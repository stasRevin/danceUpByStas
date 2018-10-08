package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserLesson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserLessonDaoTest {

    private GenericDao<UserLesson> userLessonGenericDao;
    @BeforeEach
    void setUp() {

        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
        userLessonGenericDao = new GenericDao<>(UserLesson.class);
    }

    @Test
    void getAllLessonsWhereUserIsInRoleSuccess() {

        Map<String, Map<String, String>> entities = new HashMap<>();
        Map<String, String> propertiesOne = new HashMap<>();
        Map<String, String> propertiesTwo = new HashMap<>();

        propertiesOne.put("id", "1");
        propertiesTwo.put("id", "1");
        entities.put("user", propertiesOne);
        entities.put("role", propertiesTwo);


       List<UserLesson> userLessons =  userLessonGenericDao.getElementsByEntitiesAndProperties(entities);

        assertEquals(2, userLessons.size());
    }
}