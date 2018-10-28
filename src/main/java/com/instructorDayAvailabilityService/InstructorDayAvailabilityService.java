package com.instructorDayAvailabilityService;

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
import java.util.List;

@Path("/instructorDayAvailability")
public class InstructorDayAvailabilityService {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{date}/{instructorId}")
    public Response getInstructorDayAvailability(@PathParam("date")String inputDate,
                                                 @PathParam("instructorId") String instructorIdInput ) {

        ScheduleDao scheduleDao = new ScheduleDao();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/d/yyyy");

        LocalDate date = LocalDate.parse(inputDate, dateFormatter);
        int instructorId = Integer.parseInt(instructorIdInput);

        List<LocalTime> availabilityList = scheduleDao.getAvailabilityForDateByInstructorId(date, instructorId);

        return Response.status(200).entity(availabilityList).build();
    }

}
