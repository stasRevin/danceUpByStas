package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Notification;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.UserSignInHelper;
import org.hibernate.annotations.GenericGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Notification servlet test.
 * @author srevin
 */
class NotificationServletTest {

    /**
     * The Notification dao.
     */
    GenericDao<Notification> notificationDao = new GenericDao<>(Notification.class);
    /**
     * The User dao.
     */
    GenericDao<User> userDao = new GenericDao<>(User.class);

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
    }

}