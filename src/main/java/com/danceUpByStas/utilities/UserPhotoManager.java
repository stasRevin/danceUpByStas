package com.danceUpByStas.utilities;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
