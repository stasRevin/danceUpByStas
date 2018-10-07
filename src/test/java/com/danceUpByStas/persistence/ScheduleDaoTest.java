package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleDaoTest {

    private ScheduleDao scheduleDao;
    private GenericDao<Schedule> genericDao;

    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");

        scheduleDao = new ScheduleDao();
        genericDao = new GenericDao<>(Schedule.class);
    }

    @Test
    void getScheduleByUserIdAndDateSuccess() {

        assertEquals(1, scheduleDao.getScheduleByUserIdAndDate(1, LocalDate.of(2018, 10, 27)).size());

    }

    @Test
    void getScheduleRangeSuccess() {

        List<Schedule> schedules = scheduleDao.getScheduleRangeForUser(1, LocalDate.of(2018, 9, 27), LocalDate.of(2018, 10, 01));

        assertEquals(5, schedules.size());
    }
}