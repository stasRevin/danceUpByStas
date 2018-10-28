package com.instructorDayAvailabilityService;

import com.danceUpByStas.entity.Schedule;
import com.danceUpByStas.persistence.GenericDao;
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
import java.util.ArrayList;
import java.util.List;

@Path("/instructorDayAvailability")
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

        List<LocalTime> availabilityList = scheduleDao.getAvailabilityForDateByInstructorId(date, instructorId);

        List<Schedule> schedules = getSchedulesFromAvailability(availabilityList);

        return Response.status(200).entity(schedules).build();
    }

    private List<Schedule> getSchedulesFromAvailability(List<LocalTime> availabilityList) {

        List<Schedule> schedules = new ArrayList<>();
        Schedule schedule = null;

        for (LocalTime availability : availabilityList) {

            schedule = new Schedule();
            schedule.setStartTime(availability);
            schedules.add(schedule);

        }

        return schedules;
    }

}
