package com.danceUpByStas.utilities;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserPhotoManager {

   private Logger logger = LogManager.getLogger(this.getClass());

    public void prepareUserPhoto(String userPhotoPath, String photoName) {

        String staticImagePath = "/Users/stanislavrevin/Desktop/MATC/EnterpriseJava/indieProject/target/danceup/images/userPhotos";
        String catalinaHome = System.getProperty("catalina.home");
        // String staticImagePath = catalinaHome +  File.separator + "webapps/danceup/images/userPhotos";
        File imageDirectory = new File(staticImagePath);

        Path source = Paths.get(userPhotoPath);
        Path target = Paths.get(staticImagePath + File.separator + photoName);


        SimpleVisitor simpleVisitor = new SimpleVisitor(target, source);

        try {

            Files.walkFileTree(source, simpleVisitor);

        } catch (IOException inputOutputException) {

            logger.debug("Input/Output Exception: {}",  inputOutputException);

        }

    }


    public boolean deleteUserPhoto(User user, Path photoPath) {

        boolean wasDeleted = false;
        removePhotoFromDatabase(user);
        wasDeleted = removePhotoFromUserFolder(user.getId(), photoPath);

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
                return fileName;
            }

        }

        return fileName;
    }

    public void saveUserPhoto(HttpServletRequest request, File userFolder, User user, GenericDao<User> userDao) throws  IOException, ServletException {

        for (Part part : request.getParts()) {

            if (part.getName().equals("profilePhoto")) {

                String fileName = getFileName(part);
                String fileLocation = userFolder + File.separator + fileName;
                part.write(fileLocation);
                user.setPhotoName(fileName);
                userDao.saveOrUpdate(user);
            }
        }
    }


    private void removePhotoFromDatabase(User user) {

        GenericDao<User> userDao = new GenericDao<>(User.class);
        user.setPhotoName("");
        userDao.saveOrUpdate(user);
    }


    private boolean removePhotoFromUserFolder(int userId, Path photoPath) {

        boolean wasDeleted = false;
        try {
            wasDeleted = Files.deleteIfExists(photoPath);

        } catch (IOException inputOutputException) {

            logger.debug("I/O Eception: {}", inputOutputException);
        }

        return wasDeleted;
    }


}
