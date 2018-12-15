package com.danceUpByStas.controller;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Insert instructor schedule test.
 * @author srevin
 */
class InsertInstructorScheduleTest {

    /**
     * Prepare schedule success.
     */
    @Test
    void prepareScheduleSuccess() {

        InsertInstructorSchedule servlet = new InsertInstructorSchedule();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");
        List<String> weeklySchedule = new ArrayList<>();

        weeklySchedule = Arrays.asList("8:00", "12:00", "09:00", "15:00", "16:00", "20:00", "10:00", "17:00",
                                       "17:00", "22:00", "", "", "", "");

        Map<DayOfWeek, List<LocalTime>> schedulesMap = servlet.prepareSchedule(weeklySchedule, timeFormatter);

        List<LocalTime> thursdaySchedule = schedulesMap.get(DayOfWeek.THURSDAY);
        List<LocalTime> fridaySchedule = schedulesMap.get(DayOfWeek.FRIDAY);

        assertEquals(LocalTime.of(10, 0), thursdaySchedule.get(0));
        assertEquals(LocalTime.of(17, 0), thursdaySchedule.get(1));
        assertEquals(LocalTime.of(17, 0), fridaySchedule.get(0));
        assertEquals(LocalTime.of(22, 0), fridaySchedule.get(1));

    }

}