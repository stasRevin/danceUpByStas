package com.danceUpByStas.utilities;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User photo manager test.
 * @author srevin
 */
class UserPhotoManagerTest {

    /**
     * The Logger.
     */
    Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
    }

    /**
     * Prepare user photo.
     */
    @Test
    void prepareUserPhoto() {

    }

    /**
     * Delete user photo.
     */
    @Test
    void deleteUserPhoto() {

        GenericDao<User> userDao = new GenericDao<>(User.class);
        String testPathValue = "/Users/stanislavrevin/Desktop/MATC/EnterpriseJava/indieProject/testPath";
        String photoName = "mjackson.jpg";

        Path source = Paths.get("/Users/stanislavrevin/Desktop/MATC/EnterpriseJava/indieProject/testPath/testPhotos"
                + File.separator + photoName);
        Path target = Paths.get("/Users/stanislavrevin/Desktop/MATC/EnterpriseJava/indieProject/testPath/userPhotos/1/"
                + File.separator + photoName);

        File fileOne = new File(source.toString());
        File fileOnePrime = new File(target.toString());

        try {

            SimpleVisitor simpleVisitor = new SimpleVisitor(target, source);
            Files.walkFileTree(source, simpleVisitor);

        } catch (IOException inputOutputException) {

            logger.debug("Input/Output Exception: {}",  inputOutputException);

        }

        boolean wasDeleted = false;
        boolean wasCreated = false;

        File photoFile = target.toFile();

        if (photoFile.exists()) {

            User user = (User) userDao.getById(1);
            UserPhotoManager manager = new UserPhotoManager();
            manager.deleteUserPhoto(user, target);
            wasCreated = true;

        }

        if (wasCreated && !photoFile.exists()) {

            wasDeleted = true;

        }

        assertEquals(true, wasDeleted);
    }
}