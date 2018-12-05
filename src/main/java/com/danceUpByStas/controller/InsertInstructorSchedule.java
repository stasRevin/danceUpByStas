package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Schedule;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.persistence.ScheduleDao;

import javax.servlet.RequestDispatcher;
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

/**
 * This is the InsertInstructorSchedule servlet class designed to facilitate
 * the insertion of the instructor schedule.
 * @author srevin
 */
@WebServlet(name = "InsertInstructorSchedule",
            urlPatterns = {"/insertInstructorSchedule"})
public class InsertInstructorSchedule extends HttpServlet {

    /**
     * This method handles the POST requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        ScheduleDao scheduleDao = new ScheduleDao();
        GenericDao<Schedule> scheduleGenericDao = new GenericDao<>(Schedule.class);
        String url = "/updateInstructorSchedule.jsp";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");

        String startDateInput = (String)request.getParameter("startDate");
        String endDateInput = request.getParameter("endDate");
        LocalDate startDate = null;
        LocalDate endDate = null;

        if (!Objects.isNull(startDateInput) && !startDateInput.equals("")) {

            startDate = LocalDate.parse((String)request.getParameter("startDate"), dateFormatter);
        } else {
            setErrorMessage(request);
            forward(url, request, response);
            return;
        }
        if (!Objects.isNull(endDateInput) && !endDateInput.equals("")) {

            endDate = LocalDate.parse(endDateInput, dateFormatter);

        } else {

            endDate = startDate;
        }

        String mondayStartTime = request.getParameter("mondayStartTime");
        String mondayEndTime = request.getParameter("mondayEndTime");
        String tuesdayStartTime = request.getParameter("tuesdayStartTime");
        String tuesdayEndTime = request.getParameter("tuesdayEndTime");
        String wednesdayStartTime = request.getParameter("wednesdayStartTime");
        String wednesdayEndTime = request.getParameter("wednesdayEndTime");
        String thursdayStartTime = request.getParameter("thursdayStartTime");
        String thursdayEndTime = request.getParameter("thursdayEndTime");
        String fridayStartTime = request.getParameter("fridayStartTime");
        String fridayEndTime = request.getParameter("fridayEndTime");
        String saturdayStartTime = request.getParameter("saturdayStartTime");
        String saturdayEndTime = request.getParameter("saturdayEndTime");
        String sundayStartTime = request.getParameter("sundayStartTime");
        String sundayEndTime = request.getParameter("sundayEndTime");


        List<String> weeklySchedule = Arrays.asList(mondayStartTime, mondayEndTime, tuesdayStartTime, tuesdayEndTime,
                                                       wednesdayStartTime, wednesdayEndTime, thursdayStartTime, thursdayEndTime,
                                                       fridayStartTime, fridayEndTime, saturdayStartTime, saturdayEndTime,
                                                       sundayStartTime, sundayEndTime);


        Map<DayOfWeek, List<LocalTime>> schedulesMap = prepareSchedule(weeklySchedule, timeFormatter);

        int errorCounter = scheduleDao.insertSchedulesInRangeForUser(user, startDate, endDate, schedulesMap);

        if (errorCounter > 0) {
            setErrorMessage(request);
        }

        List<Schedule> schedules = scheduleGenericDao.getElementsOfTypeAByIdOfEntityOfTypeB("user", user.getId());

        session.setAttribute("schedules", schedules);

        forward(url, request, response);

    }

    /**
     * This method prepares instructor's schedule by producing a schedule map to be inserted.
     * @param weeklySchedule The weekly schedule provided by the instructor.
     * @param timeFormatter The time formatter.
     * @return scheduleMap. The instructor schedule map.
     */
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

    /**
     * This method sets the error message.
     * @param request
     */
    private void setErrorMessage(HttpServletRequest request) {

        request.setAttribute("insertError", "Not all schedules have been inserted. No duplicates are allowed. " +
                "The days of week selected must be within the provided date range. Please verify your input and try again.");
    }

    /**
     * This method forwards request and response to a specified URL.
     * @param url The target URL.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws IOException The input output exception.
     * @throws ServletException The servlet exception.
     */
    private void forward(String url, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }

    /**
     * This utility method adds to the instructor's schedule.
     * @param daySchedule The list of schedule entries.
     * @param currentSchedule The current schedule entry to add to the list.
     * @param formatter The date and time formatter.
     */
    private void addToSchedule(List<LocalTime> daySchedule, String currentSchedule, DateTimeFormatter formatter) {

        if (currentSchedule != null && !currentSchedule.isEmpty()
                && currentSchedule.matches("^(([0-1]?[0-9])|([2][0-3])):[0-5][0-9]$")) {

            daySchedule.add(LocalTime.parse(currentSchedule, formatter));

        }

    }

}
