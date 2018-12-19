package com.instructorDayAvailabilityService;

import com.danceUpByStas.entity.Schedule;
import com.danceUpByStas.persistence.ScheduleDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

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
 * This is the InstructorDayAvailabilityService designed to produce availability schedule for a given instructor.
 * @author srevin
 */
@Path("/instructor-availabilities")
public class InstructorDayAvailabilityService {

    /**
     * This method returns availability schedule for a given instructor.
     * @param inputDate The date for which availability is being requested.
     * @param instructorIdInput The instructor's id.
     * @return response The service response.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{date}/{instructorId}")
    public Response getInstructorDayAvailability(@PathParam("date")String inputDate,
                                                 @PathParam("instructorId") String instructorIdInput ) {

        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("appContext.xml");
        ScheduleDao scheduleDao = applicationContext.getBean("scheduleDao", ScheduleDao.class);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(inputDate, dateFormatter);
        int instructorId = Integer.parseInt(instructorIdInput);

        Set<LocalTime> availabilitySet = scheduleDao.getAvailabilityForDateByInstructorId(date, instructorId);
        Set<Schedule> schedules = getSchedulesFromAvailability(availabilitySet);

        return Response.status(200).entity(schedules).build();
    }

    /**
     * This method returns a set of schedules from given availability set.
     * @param availabilitySet The set of availability.
     * @return schedules The set of schedules.
     */
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
