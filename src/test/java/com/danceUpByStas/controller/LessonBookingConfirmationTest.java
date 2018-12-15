package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Notification;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.UserSignInHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Lesson booking confirmation test.
 * @author srevin
 */
class LessonBookingConfirmationTest {

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

    /**
     * Insert lesson.
     */
    @Test
    void insertLesson() {
    }

    /**
     * Gets location.
     */
    @Test
    void getLocation() {
    }

    /**
     * Gets instructor.
     */
    @Test
    void getInstructor() {
    }

    /**
     * Insert user lesson.
     */
    @Test
    void insertUserLesson() {
    }

    /**
     * Gets user role.
     */
    @Test
    void getUserRole() {
    }

    /**
     * Create notification.
     */
    @Test
    void createNotification() {
    }

    /**
     * Gets notifications.
     */
    @Test
    void getNotifications() {

        LessonBookingConfirmation servlet = new LessonBookingConfirmation();
        UserSignInHelper helper = new UserSignInHelper();
        User user = userDao.getById(1);
        List<Notification> notificationList = helper.getNotifications(user);

        assertEquals(1, notificationList.size());
    }
}