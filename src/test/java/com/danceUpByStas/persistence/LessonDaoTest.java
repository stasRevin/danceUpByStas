package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Lesson;
import com.danceUpByStas.entity.Location;
import org.hibernate.annotations.GenericGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LessonDaoTest {

    private LessonDao lessonDao;
    private GenericDao<Lesson> genericDao;
    private GenericDao<Location> genericDaoLocation;

    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
        this.lessonDao = new LessonDao();
        genericDao = new GenericDao<>(Lesson.class);
        genericDaoLocation = new GenericDao<>(Location.class);
    }

    @Test
    void getLocationByLessonIdSuccess() {

        List<Lesson> lessons = lessonDao.getLocationByLessonId(1);
        assertEquals("My Ballroom", lessons.get(0).getLocation().getName());
    }

    @Test
    void insertLessonSuccess() {

        Location location = genericDaoLocation.getById(1);
        LocalDateTime startTime = LocalDateTime.of(2018, 9, 29, 17, 0);
        LocalDateTime endTime = LocalDateTime.of(2018, 9, 29, 18, 0);
        int id = genericDao.insert(new Lesson(startTime, endTime, location));
        Lesson lessonReceived = genericDao.getById(id);
        assertEquals(startTime, lessonReceived.getStartTime());
    }
}