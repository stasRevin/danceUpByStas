package com.danceUpByStas.utilities;

import com.danceUpByStas.entity.Lesson;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserLesson;
import com.danceUpByStas.persistence.GenericDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserSignInHelperTest {

    GenericDao<UserLesson> userLessonDao = new GenericDao<>(UserLesson.class);

    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
    }

    @Test
    void getUserDances() {
    }

    @Test
    void getUserSchedule() {
    }

    @Test
    void getUserLessons() {
    }

    @Test
    void setUserDances() {
    }

    @Test
    void getStudentsForLessons() {

        UserSignInHelper helper = new UserSignInHelper();

        List<UserLesson> userLessons = userLessonDao.getAll();

        Lesson lesson = userLessons.stream().filter(l -> l.getRole().getId() == 2).findFirst().get().getLesson();

        userLessons = helper.getUsersForLessons(userLessons, 2);

        assertEquals("mjessy", userLessons.get(0).getLesson().getStudents().get(0).getUsername());
    }
}