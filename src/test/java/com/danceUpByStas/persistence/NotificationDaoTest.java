package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Notification;
import com.danceUpByStas.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NotificationDaoTest {

    private GenericDao<Notification> notificationDao = new GenericDao<>(Notification.class);
    private GenericDao<User> userDao = new GenericDao<>(User.class);

    @BeforeEach
    void SetUp() {

        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
    }

    @Test
    void createNotificationSuccess() {

        String messageForInstructor = "A lesson was scheduled with the student Jake Myers at Jimmy's Ballroom on 09/25/2018 at 3PM.";
        String messageForStudent = "A lesson was scheduled with the instructor Dancing Bobby at Jimmy's Ballroom on 09/25/2018 at 3PM.";

        User student = new User("pavlik", "abc", 0, "Pablo", "Escobar",
                "356 Sheridan Rd", "", "Madison", "WI", "53705", 0.0, "");

        int studentId = userDao.insert(student);
        student = userDao.getById(studentId);

        User instructor = userDao.getById(1);

        Notification notificationForInstructor = new Notification(messageForInstructor, instructor);
        Notification notificationForStudent = new Notification(messageForStudent, student);

        int instructorNotificationId = notificationDao.insert(notificationForInstructor);
        int studentNotificationId = notificationDao.insert(notificationForStudent);

        notificationForInstructor = notificationDao.getById(instructorNotificationId);
        notificationForStudent = notificationDao.getById(studentNotificationId);

        assertEquals(messageForInstructor, notificationForInstructor.getMessage());
        assertEquals(messageForStudent, notificationForStudent.getMessage());

    }
}
