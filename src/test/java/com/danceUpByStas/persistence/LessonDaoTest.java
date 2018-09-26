package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Lesson;
import com.danceUpByStas.entity.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LessonDaoTest {

    private LessonDao lessonDao;
    private GenericDao<Lesson> genericDao;


    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
        this.lessonDao = new LessonDao();
    }

    @Test
    void getLocationByLessonIdSuccess() {

        List<Lesson> lessons = lessonDao.getLocationByLessonId(1);
        assertEquals("My Ballroom", lessons.get(0).getLocation().getName());

    }
}