package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Schedule;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;

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

@WebServlet(name = "DeleteInstructorSchedule",
            urlPatterns = {"/deleteInstructorSchedule"})
public class DeleteInstructorSchedule extends HttpServlet {
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<Schedule> schedules = (List<Schedule>) session.getAttribute("schedules");
        User user = (User) session.getAttribute("user");

        LocalDate date = LocalDate.parse((String)request.getParameter("date"));
        LocalTime starTime = LocalTime.parse((String)request.getParameter("startTime"));
        LocalTime endTime = LocalTime.parse((String)request.getParameter("endTime"));

        Schedule scheduleToDelete = schedules.stream().filter(s -> s.getDate().compareTo(date) == 0
                                    && s.getStartTime().compareTo(starTime) == 0
                                    && s.getEndTime().compareTo(endTime) == 0).findFirst().get();

        GenericDao<Schedule> scheduleGenericDao = new GenericDao<>(Schedule.class);

        scheduleGenericDao.delete(scheduleToDelete);
        schedules = scheduleGenericDao.getElementsOfTypeAByIdOfEntityOfTypeB("user", user.getId());

        session.setAttribute("schedules", schedules);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doDelete(request, response);
    }
}
