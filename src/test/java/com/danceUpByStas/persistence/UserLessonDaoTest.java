package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.UserLesson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserLessonDaoTest {

    private UserLessonDao userLessonDao;
    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
        userLessonDao = new UserLessonDao();
    }

    @Test
    void getLessonsByUserIdSuccess() {

        List<UserLesson> userLessons = userLessonDao.getLessonsByUserId(1);
        assertEquals(2, userLessons.size());
        assertEquals("Michael", userLessons.get(0).getUser().getFirstName());

    }

    @Test
    void getUsersByLessonIdSuccess() {
    }
}