package com.instructorDayAvailabilityService;

import com.danceUpByStas.entity.Schedule;
import com.danceUpByStas.persistence.ScheduleDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * This is the InstructorDayAvailabilityService
 * @author srevin
 */
@Path("/instructor-availabilities")
public class InstructorDayAvailabilityService {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{date}/{instructorId}")
    public Response getInstructorDayAvailability(@PathParam("date")String inputDate,
                                                 @PathParam("instructorId") String instructorIdInput ) {

        ScheduleDao scheduleDao = new ScheduleDao();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(inputDate, dateFormatter);
        int instructorId = Integer.parseInt(instructorIdInput);

        Set<LocalTime> availabilitySet = scheduleDao.getAvailabilityForDateByInstructorId(date, instructorId);
        Set<Schedule> schedules = getSchedulesFromAvailability(availabilitySet);

        return Response.status(200).entity(schedules).build();
    }

    private Set<Schedule> getSchedulesFromAvailability(Set<LocalTime> availabilitySet) {

        Set<Schedule> schedules = new TreeSet<>();

        for (LocalTime availability : availabilitySet) {

            Schedule schedule = new Schedule();
            schedule.setStartTime(availability);
            schedules.add(schedule);
        }

        return schedules;
    }

}
