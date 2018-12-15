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

/**
 * The type User sign in helper test.
 * @author srevin
 */
class UserSignInHelperTest {

    /**
     * The User lesson dao.
     */
    GenericDao<UserLesson> userLessonDao = new GenericDao<>(UserLesson.class);

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
    }

    /**
     * Gets user dances.
     */
    @Test
    void getUserDances() {
    }

    /**
     * Gets user schedule.
     */
    @Test
    void getUserSchedule() {
    }

    /**
     * Gets user lessons.
     */
    @Test
    void getUserLessons() {
    }

    /**
     * Sets user dances.
     */
    @Test
    void setUserDances() {
    }

    /**
     * Gets students for lessons.
     */
    @Test
    void getStudentsForLessons() {

        UserSignInHelper helper = new UserSignInHelper();

        List<UserLesson> userLessons = userLessonDao.getAll();

        Lesson lesson = userLessons.stream().filter(l -> l.getRole().getId() == 2).findFirst().get().getLesson();

        userLessons = helper.getUsersForLessons(userLessons, 2);

        assertEquals("mjessy", userLessons.get(0).getLesson().getStudents().get(0).getUsername());
    }
}