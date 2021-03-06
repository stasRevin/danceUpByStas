package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Notification;
import com.danceUpByStas.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Notification dao test.
 * @author srevin
 */
class NotificationDaoTest {

    private GenericDao<Notification> notificationDao = new GenericDao<>(Notification.class);
    private GenericDao<User> userDao = new GenericDao<>(User.class);

    /**
     * Set up.
     */
    @BeforeEach
    void SetUp() {

        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
    }

    /**
     * Create notification success.
     */
    @Test
    void createNotificationSuccess() {

        String messageForInstructor = "A lesson was scheduled with the student Jake Myers at Jimmy's Ballroom on 09/25/2018 at 3PM.";
        String messageForStudent = "A lesson was scheduled with the instructor Dancing Bobby at Jimmy's Ballroom on 09/25/2018 at 3PM.";

        User student = new User("pavlik", "abc", 0, "Pablo", "Escobar",
                "356 Sheridan Rd", "Madison", "WI", "53705", 0.0, "");

        int studentId = userDao.insert(student);
        student = userDao.getById(studentId);

        User instructor = userDao.getById(1);
        LocalDateTime currentDateTime = LocalDateTime.now();

        Notification notificationForInstructor = new Notification(messageForInstructor, instructor, 0, currentDateTime);
        Notification notificationForStudent = new Notification(messageForStudent, student, 0, currentDateTime);

        int instructorNotificationId = notificationDao.insert(notificationForInstructor);
        int studentNotificationId = notificationDao.insert(notificationForStudent);

        notificationForInstructor = notificationDao.getById(instructorNotificationId);
        notificationForStudent = notificationDao.getById(studentNotificationId);

        assertEquals(messageForInstructor, notificationForInstructor.getMessage());
        assertEquals(messageForStudent, notificationForStudent.getMessage());

    }
}
