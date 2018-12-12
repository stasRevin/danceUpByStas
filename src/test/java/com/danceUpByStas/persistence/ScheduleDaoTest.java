package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Schedule;
import com.danceUpByStas.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleDaoTest {

    private ScheduleDao scheduleDao;
    private GenericDao<User> userDao;

    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");

        scheduleDao = new ScheduleDao();
        userDao = new GenericDao<>(User.class);
    }

    @Test
    void getScheduleByUserIdAndDateSuccess() {

        assertEquals(1, scheduleDao.getScheduleByUserIdAndDate(1, LocalDate.of(2018, 10, 27)).size());

    }

    @Test
    void getScheduleRangeSuccess() {

        List<Schedule> schedules = scheduleDao.getScheduleRangeForUser(1, LocalDate.of(2018, 9, 27),
                LocalDate.of(2018, 10, 1));

        assertEquals(5, schedules.size());
    }

    @Test
    void insertSchedulesInRangeForUserSuccess() {

        Map<DayOfWeek, List<LocalTime>> schedules = new HashMap<>();

        List<LocalTime> mondaySchedule = new ArrayList();
        List<LocalTime> tuesdaySchedule = new ArrayList<>();
        List<LocalTime> wednesdaySchedule = new ArrayList<>();
        List<LocalTime> thursdaySchedule = new ArrayList<>();
        List<LocalTime> fridaySchedule = new ArrayList<>();

        mondaySchedule.add(LocalTime.of(17, 0));
        mondaySchedule.add(LocalTime.of(22, 0));
        tuesdaySchedule.add(LocalTime.of(17, 0));
        tuesdaySchedule.add(LocalTime.of(20, 0));
        wednesdaySchedule.add(LocalTime.of(16, 0));
        wednesdaySchedule.add(LocalTime.of(19, 0));
        thursdaySchedule.add(LocalTime.of(17, 0));
        thursdaySchedule.add(LocalTime.of(22, 0));
        fridaySchedule.add(LocalTime.of(14, 0));
        fridaySchedule.add(LocalTime.of(19, 0));

        schedules.put(DayOfWeek.MONDAY, mondaySchedule);
        schedules.put(DayOfWeek.TUESDAY, tuesdaySchedule);
        schedules.put(DayOfWeek.WEDNESDAY, wednesdaySchedule);
        schedules.put(DayOfWeek.THURSDAY, thursdaySchedule);
        schedules.put(DayOfWeek.FRIDAY, fridaySchedule);

        User user = userDao.getById(1);

        scheduleDao.insertSchedulesInRangeForUser(user,
                                                  LocalDate.of(2018, 11, 22),
                                                  LocalDate.of(2018, 12, 5),
                                                  schedules);

        List<Schedule> schedulesRetrieved = scheduleDao.getScheduleRangeForUser(1, LocalDate.of(2018, 11, 22), LocalDate.of(2018, 12, 5));

        assertEquals(10, schedulesRetrieved.size());

    }

    @Test
    void getAvailabilityForDateByInstructorIdSuccess() {

        Set<LocalTime> availableTimes = scheduleDao.getAvailabilityForDateByInstructorId(LocalDate.of(2018, 9, 27), 1);
        assertEquals(4, availableTimes.size());
    }
}