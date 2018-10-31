package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Schedule;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.persistence.ScheduleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet(name = "InsertInstructorSchedule",
            urlPatterns = {"/insertInstructorSchedule"})
public class InsertInstructorSchedule extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //TODO add day of week to the parameters in jsp
        HttpSession session = request.getSession(false);

        User user = (User)session.getAttribute("user");
        ScheduleDao scheduleDao = new ScheduleDao();
        GenericDao<Schedule> scheduleGenericDao = new GenericDao<>(Schedule.class);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");
        LocalDate startDate = LocalDate.parse((String)request.getParameter("startDate"), dateFormatter);
        LocalDate endDate = LocalDate.parse((String)request.getParameter("endDate"), dateFormatter);

        String mondayStartTime = (String)request.getParameter("mondayStartTime");
        String mondayEndTime = (String)request.getParameter("mondayEndTime");
        String tuesdayStartTime = (String) request.getParameter("tuesdayStartTime");
        String tuesdayEndTime = (String) request.getParameter("tuesdayEndTime");
        String wednesdayStartTime = (String) request.getParameter("wednesdayStartTime");
        String wednesdayEndTime = (String) request.getParameter("wednesdayEndTime");
        String thursdayStartTime = (String) request.getParameter("thursdayStartTime");
        String thursdayEndTime = (String) request.getParameter("thursdayEndTime");
        String fridayStartTime = (String) request.getParameter("fridayStartTime");
        String fridayEndTime = (String) request.getParameter("fridayEndTime");
        String saturdayStartTime = (String) request.getParameter("saturdayStartTime");
        String saturdayEndTime = (String) request.getParameter("saturdayEndTime");
        String sundayStartTime = (String) request.getParameter("sundayStartTime");
        String sundayEndTime = (String) request.getParameter("sundayEndTime");


        List<String> weeklySchedule = Arrays.asList(mondayStartTime, mondayEndTime, tuesdayStartTime, tuesdayEndTime,
                                                       wednesdayStartTime, wednesdayEndTime, thursdayStartTime, thursdayEndTime,
                                                       fridayStartTime, fridayEndTime, saturdayStartTime, saturdayEndTime,
                                                       sundayStartTime, sundayEndTime);


        Map<DayOfWeek, List<LocalTime>> schedulesMap = prepareSchedule(weeklySchedule, timeFormatter);

        int insertedId = scheduleDao.insertSchedulesInRangeForUser(user, startDate, endDate, schedulesMap);

        List<Schedule> schedules = scheduleGenericDao.getElementsOfTypeAByIdOfEntityOfTypeB("user", user.getId());

        session.setAttribute("schedules", schedules);

        response.sendRedirect("/danceup/updateInstructorSchedule.jsp");


    }

    public Map<DayOfWeek, List<LocalTime>> prepareSchedule(List<String> weeklySchedule, DateTimeFormatter timeFormatter) {

        int counter = 1;
        int dayCounter = 1;
        List<LocalTime> daySchedule = null;
        Map<DayOfWeek, List<LocalTime>> schedulesMap = new HashMap<>();


        for (String currentSchedule : weeklySchedule) {

            currentSchedule = currentSchedule.trim();

            if (counter % 2 != 0) {

                daySchedule = new ArrayList<>();
                addToSchedule(daySchedule, currentSchedule, timeFormatter);

            } else {

                addToSchedule(daySchedule, currentSchedule, timeFormatter);

                if (daySchedule.size() > 1) {

                    schedulesMap.put(DayOfWeek.of(dayCounter), daySchedule);
                }

                dayCounter += 1;

            }

            counter += 1;

        }

        return schedulesMap;

    }


    private void addToSchedule(List<LocalTime> daySchedule, String currentSchedule, DateTimeFormatter formatter) {

        if (currentSchedule != null && !currentSchedule.isEmpty()
                && currentSchedule.matches("^(([0-1]?[0-9])|([2][0-3])):[0-5][0-9]$")) {

            daySchedule.add(LocalTime.parse(currentSchedule, formatter));

        }

    }

}
