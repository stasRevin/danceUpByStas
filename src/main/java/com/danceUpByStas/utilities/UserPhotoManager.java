package com.danceUpByStas.utilities;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

/**
 * This is the UserPhotoManager helper class designed to perform user photo related operations.
 * @author srevin
 */
public class UserPhotoManager implements PropertiesLoader {

   private Logger logger = LogManager.getLogger(this.getClass());
   private final Properties PROPERTIES = loadProperties("/photoManagerRemote.properties");

    /**
     * This method prepares the user photo for the later use in the view.
     * @param userPhotoPath The path of the photograph.
     * @param photoDirectory The photo directory.
     * @param photoName The name of the photo.
     */
    public void prepareUserPhoto(String userPhotoPath, String photoDirectory, String photoName) {

        String staticImagePath = PROPERTIES.getProperty("staticImagePath");
        String catalinaHome = System.getProperty("catalina.home");
        staticImagePath = catalinaHome +  File.separator + staticImagePath;
        String targetPathName = staticImagePath + File.separator + photoDirectory;
        createPhotoDirectory(targetPathName);
        Path source = Paths.get(userPhotoPath);
        Path target = Paths.get(targetPathName + File.separator + photoName);

        logger.debug("Source: " + source.toString());
        logger.debug("Target: " + target.toString());
        logger.debug("Path: " + staticImagePath + File.separator + photoDirectory + File.separator + photoName);

        SimpleVisitor simpleVisitor = new SimpleVisitor(target, source);

        try {

            Files.walkFileTree(source, simpleVisitor);

        } catch (IOException inputOutputException) {

            logger.debug("Input/Output Exception: {}",  inputOutputException);
        }
    }

    /**
     * This method creates photo directory.
     * @param targetPathName The target path name.
     */
    private void createPhotoDirectory(String targetPathName) {

        File photoFile = new File(targetPathName);

        logger.debug("Creating photo directory: " + photoFile.toString());
        createPhotoDirectory(photoFile);
    }

    /**
     * This method creates photo directory.
     * @param userFolder
     */
    private void createPhotoDirectory(File userFolder) {

        if (!userFolder.exists()) {

            userFolder.mkdir();
        }
    }

    /**
     * This method deletes user photo.
     * @param user The reference to the user object.
     * @param photoPath The photo path.
     * @return true/false The result of the whether the delete operation succeeded.
     */
    public boolean deleteUserPhoto(User user, Path photoPath) {

        boolean wasDeleted = false;
        removePhotoFromDatabase(user);
        wasDeleted = removePhotoFromUserFolder(photoPath);

        return wasDeleted;
    }

    /**
     * This method gets the file name.
     * @param part The input part to be parsed.
     * @return fileName The file name.
     */
    private String getFileName(Part part) {

        String contentDisplay = part.getHeader("content-disposition");
        String fileName = "";
        String[] contentItems = contentDisplay.split(";");

        for (String contentItem : contentItems) {

            if (contentItem.trim().startsWith("filename")) {

                fileName = contentItem.substring(contentItem.indexOf("=") + 2,
                        contentItem.length() - 1);
                return Objects.isNull(fileName) || fileName.equals("") ? "" : fileName;
            }

        }

        return fileName;
    }

    /**
     * This method saves the user photo.
     * @param request The HTTP request.
     * @param userFolder The user directory.
     * @param user The reference to the user object.
     * @param userDao The reference to the generic user data access object.
     * @throws IOException The input/output exception.
     * @throws ServletException The servlet exception.
     */
    public void saveUserPhoto(HttpServletRequest request, File userFolder, User user, GenericDao<User> userDao)
                throws  IOException, ServletException {

        createPhotoDirectory(userFolder);
        for (Part part : request.getParts()) {

            if (part.getName().equals("profilePhoto")) {

                String photoPart = getFileName(part);
                if (Objects.isNull(photoPart) || photoPart.equals("")) {

                    break;
                }

                String fileName = user.getId() + getFileName(part);
                String fileLocation = userFolder + File.separator + fileName;
                part.write(fileLocation);
                user.setPhotoName(fileName);
                userDao.saveOrUpdate(user);
            }
        }
    }

    /**
     * This method removes the name of the photo from the database for the given user.
     * @param user The reference to the given user object.
     */
    private void removePhotoFromDatabase(User user) {

        GenericDao<User> userDao = new GenericDao<>(User.class);
        user.setPhotoName("");
        userDao.saveOrUpdate(user);
    }

    /**
     * This method removes photo from the user directory.
     * @param photoPath The path of the photo.
     * @return true/false on whether photo was deleted.
     */
    private boolean removePhotoFromUserFolder(Path photoPath) {

        boolean wasDeleted = false;
        try {
            wasDeleted = Files.deleteIfExists(photoPath);

        } catch (IOException inputOutputException) {

            logger.debug("I/O Eception: {}", inputOutputException);
        }

        return wasDeleted;
    }

    /**
     * This method deletes the directory of photos viewed by user.
     * @param directoryToDelete The directory to remove.
     */
    void deleteUserPhotos(File directoryToDelete) {

        File[] fileList = directoryToDelete.listFiles();

        if (fileList != null && fileList.length > 0) {

            for (File file : fileList) {

                file.delete();
            }
        }

        directoryToDelete.delete();
    }

    /**
     * This method verifies whether the user photo exists.
     * @param userPhotoDirectory The user photo directory.
     * @param photoName The photo name.
     * @return true/false on whether photo exists.
     */
    public boolean checkIfUserPhotoExists(String userPhotoDirectory, String photoName) {

        File photoFile = new File(userPhotoDirectory + File.separator + photoName);

        if (photoFile.exists() && !photoFile.isDirectory()) {

            return  true;
        }
        return false;
    }

    /**
     * This method prepares the photos of the users that were found in the search by another user.
     * @param loggedInUserId The id of the currently logged in user.
     * @param foundUsers The set of references to the user objects found.
     * @param context The servlet context.
     */
    public void prepareFoundUsersPhotos(String loggedInUserId, Set<User> foundUsers, ServletContext context) {

        String userPhotoPath = "";
        String photoName = "";

        for (User foundUser : foundUsers) {

            logger.debug("In prepareFoundUsersPhotos, preparing photo for user {}.", foundUser.getFirstName());
            photoName = foundUser.getPhotoName();
            userPhotoPath = (String)context.getAttribute("profilePhotoPath")
                    + File.separator + foundUser.getId() + File.separator + photoName;
            String photoDirectoryName = (String)context.getAttribute("usersFoundPhotosDirectory");
            prepareUserPhoto(userPhotoPath, photoDirectoryName + loggedInUserId, photoName);
        }
    }
}
