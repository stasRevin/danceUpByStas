package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Schedule;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * This is the DeleteInstructorSchedule servlet class designed to facilitate the removal of instructor's schedule.
 * @author srevin
 */
@WebServlet(name = "DeleteInstructorSchedule",
            urlPatterns = {"/deleteInstructorSchedule"})
public class DeleteInstructorSchedule extends HttpServlet {

    /**
     * This method responds to the DELETE requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input/output exception.
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession(false);

        List<Schedule> schedules = (List<Schedule>) session.getAttribute("schedules");
        User user = (User) session.getAttribute("user");
        Logger logger = LogManager.getLogger(this.getClass());
        LocalDate date = LocalDate.parse((String)request.getParameter("date"));
        LocalTime starTime = LocalTime.parse((String)request.getParameter("startTime"));
        LocalTime endTime = LocalTime.parse((String)request.getParameter("endTime"));

        Schedule scheduleToDelete = schedules.stream().filter(s -> s.getDate().compareTo(date) == 0
                                    && s.getStartTime().compareTo(starTime) == 0
                                    && s.getEndTime().compareTo(endTime) == 0).findFirst().get();

        GenericDao<Schedule> scheduleGenericDao = new GenericDao<>(Schedule.class);

        try {
        scheduleGenericDao.delete(scheduleToDelete);
        schedules = scheduleGenericDao.getElementsOfTypeAByIdOfEntityOfTypeB("user", user.getId());
        } catch (Exception exception) {

            logger.debug("Problem removing or getting schedule: {}", exception);
        }

        session.setAttribute("schedules", schedules);
    }

    /**
     * This method responds to the GET requests.
     * @param request The instance of HTTP request
     * @param response The instance of HTTP response
     * @throws ServletException The servlet exception.
     * @throws IOException The input/output exception.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doDelete(request, response);
    }
}
