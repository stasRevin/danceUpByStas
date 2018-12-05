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

public class UserPhotoManager implements PropertiesLoader {

   private Logger logger = LogManager.getLogger(this.getClass());
   private final Properties PROPERTIES = loadProperties("/photoManagerRemote.properties");

    public void prepareUserPhoto(String userPhotoPath, String photoDirectory, String photoName) {

        String staticImagePath = PROPERTIES.getProperty("staticImagePath");

        String catalinaHome = System.getProperty("catalina.home");

        staticImagePath = catalinaHome +  File.separator + staticImagePath;

        //create photoDirectory
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

    private void createPhotoDirectory(String targetPathName) {

        File photoFile = new File(targetPathName);

        logger.debug("Creating photo directory: " + photoFile.toString());


        if (!photoFile.exists()) {

            photoFile.mkdir();

        }

    }

    public boolean deleteUserPhoto(User user, Path photoPath) {

        boolean wasDeleted = false;
        removePhotoFromDatabase(user);
        wasDeleted = removePhotoFromUserFolder(photoPath);

        return wasDeleted;

    }

    public String getFileName(Part part) {

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

    public void saveUserPhoto(HttpServletRequest request, File userFolder, User user, GenericDao<User> userDao)
                throws  IOException, ServletException {

        createUserPhotoFolder(userFolder);
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

    private void createUserPhotoFolder(File userFolder) {

        if (!userFolder.exists()) {

            userFolder.mkdir();
        }
    }


    private void removePhotoFromDatabase(User user) {

        GenericDao<User> userDao = new GenericDao<>(User.class);
        user.setPhotoName("");
        userDao.saveOrUpdate(user);
    }


    public boolean removePhotoFromUserFolder(Path photoPath) {

        boolean wasDeleted = false;
        try {
            wasDeleted = Files.deleteIfExists(photoPath);

        } catch (IOException inputOutputException) {

            logger.debug("I/O Eception: {}", inputOutputException);
        }

        return wasDeleted;
    }


    public void deleteUserPhotos(File directoryToDelete) {

        File[] fileList = directoryToDelete.listFiles();

        if (fileList != null && fileList.length > 0) {

            for (File file : fileList) {

                file.delete();
            }

        }

        directoryToDelete.delete();

    }

    public boolean checkIfUserPhotoExists(String userPhotoDirectory, String photoName) {

        File photoFile = new File(userPhotoDirectory + File.separator + photoName);

        if (photoFile.exists() && !photoFile.isDirectory()) {

            return  true;
        }

        return false;
    }


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
