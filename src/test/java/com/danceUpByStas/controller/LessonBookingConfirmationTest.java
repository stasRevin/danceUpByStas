package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Notification;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.UserSignInHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LessonBookingConfirmationTest {

    GenericDao<User> userDao = new GenericDao<>(User.class);
    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
    }

    @Test
    void insertLesson() {
    }

    @Test
    void getLocation() {
    }

    @Test
    void getInstructor() {
    }

    @Test
    void insertUserLesson() {
    }

    @Test
    void getUserRole() {
    }

    @Test
    void createNotification() {
    }

    @Test
    void getNotifications() {

        LessonBookingConfirmation servlet = new LessonBookingConfirmation();
        UserSignInHelper helper = new UserSignInHelper();
        User user = userDao.getById(1);
        List<Notification> notificationList = helper.getNotifications(user);

        assertEquals(1, notificationList.size());
    }
}