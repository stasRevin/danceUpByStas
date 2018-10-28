package com.danceUpByStas.controller;

import com.danceUpByStas.entity.*;
import com.danceUpByStas.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "LessonBookingConfirmation",
            urlPatterns= {"/bookingConfirmation"})

public class LessonBookingConfirmation extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger logger = LogManager.getLogger(this.getClass());
        HttpSession session = request.getSession(false);

        User user = (User)session.getAttribute("user");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        //get start time, end time, location id, date

        String startTimeInput = request.getParameter("lessonTime");
        String dateInput = request.getParameter("lessonDate");
        String locationIdInput = request.getParameter("location");
        String instructorIdInput = request.getParameter("instructor");

        logger.debug("startTime: {}, date: {}, locationId: {}, instructorId: {}", startTimeInput, dateInput, locationIdInput, instructorIdInput);

        LocalTime lessonStartTime = LocalTime.parse(startTimeInput);
        LocalTime lessonEndTime = lessonStartTime.plusHours(1);
        LocalDate lessonDate = LocalDate.parse(dateInput, dateFormatter);
        int locationId = Integer.parseInt(locationIdInput);
        int instructorId = Integer.parseInt(instructorIdInput);

        //create a lesson
        GenericDao<Lesson> lessonDao = new GenericDao<>(Lesson.class);
        GenericDao<Location> locationDao = new GenericDao<>(Location.class);
        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<UserLesson> userLessonDao = new GenericDao<>(UserLesson.class);
        GenericDao<Role> roleDao = new GenericDao<Role>(Role.class);

        Location location = locationDao.getById(locationId);

        Lesson lesson = new Lesson(lessonStartTime, lessonEndTime, location, lessonDate);
        lessonDao.insert(lesson);

        //associate lesson with student

        Role studentRole = roleDao.getById(2);
        userLessonDao.insertManyToMany(new UserLesson(user, lesson, studentRole));

        //associate lesson with instructor
        Role instructorRole = roleDao.getById(1);
        User instructor = userDao.getById(instructorId);

        userLessonDao.insertManyToMany(new UserLesson(instructor, lesson, instructorRole));

        request.setAttribute("instructor", instructor);
        request.setAttribute("lesson", lesson);
        request.setAttribute("location", location);


        //create notification for student

        //create notification for instructor

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookingConfirmation.jsp");
        dispatcher.forward(request, response);

    }

}
