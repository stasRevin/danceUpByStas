package com.instructorDayAvailabilityService;

import com.danceUpByStas.entity.Schedule;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class InstructorDayAvailabilityServiceTest {

    @Test
    void getInstructorDayAvailabilitySuccess() {

        InstructorDayAvailabilityService service = new InstructorDayAvailabilityService();
        //List<LocalTime> availableTimes = scheduleDao.getAvailabilityForDateByInstructorId(LocalDate.of(2018, 9, 27), 1);
        Response response = service.getInstructorDayAvailability("2018-09-27", "1");

        Set<Schedule> schedules = (Set<Schedule>)response.getEntity();

        assertEquals(4, schedules.size());
    }
}