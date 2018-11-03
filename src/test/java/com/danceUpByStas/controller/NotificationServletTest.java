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

class NotificationServletTest {

    GenericDao<Notification> notificationDao = new GenericDao<>(Notification.class);
    GenericDao<User> userDao = new GenericDao<>(User.class);

    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
    }

    @Test
    void markAsReadSuccess() {
        User user = userDao.getById(1);

        NotificationServlet servlet = new NotificationServlet();
        UserSignInHelper helper = new UserSignInHelper();
        List<Notification> notificationList = helper.getNotifications(user);

        servlet.markAsRead(notificationList);

        notificationList = helper.getNotifications(user);

        assertEquals(0, notificationList.size());

    }
}