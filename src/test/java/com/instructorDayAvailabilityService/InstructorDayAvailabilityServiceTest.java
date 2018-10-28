package com.instructorDayAvailabilityService;

import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InstructorDayAvailabilityServiceTest {

    @Test
    void getInstructorDayAvailabilitySuccess() {

        InstructorDayAvailabilityService service = new InstructorDayAvailabilityService();
        //List<LocalTime> availableTimes = scheduleDao.getAvailabilityForDateByInstructorId(LocalDate.of(2018, 9, 27), 1);
        Response response = service.getInstructorDayAvailability("09/27/2018", "1");

        List<LocalTime> availabilityList = (List<LocalTime>)response.getEntity();

        System.out.println(availabilityList);
        assertEquals(4, availabilityList.size());
    }
}