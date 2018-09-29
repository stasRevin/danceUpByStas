package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleDaoTest {

    private ScheduleDao scheduleDao;

    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");

        scheduleDao = new ScheduleDao();
    }

    @Test
    void getScheduleByUserIdAndDateSuccess() {

        assertEquals(1, scheduleDao.getScheduleByUserIdAndDate(1, LocalDate.of(2018, 10, 27)).size());

    }
}