package com.danceUpByStas.controller;

import com.danceUpByStas.entity.*;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.UserSignInHelper;
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
import java.util.List;

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

        Location location = getLocation(locationId);

        Lesson lesson = new Lesson(lessonStartTime, lessonEndTime, location, lessonDate);
        insertLesson(lesson);

        //associate lesson with student

        Role studentRole = getUserRole(2);
        insertUserLesson(user, lesson, studentRole);

        //associate lesson with instructor
        Role instructorRole = getUserRole(1);
        User instructor = getInstructor(instructorId);

        insertUserLesson(instructor, lesson, instructorRole);

        request.setAttribute("instructor", instructor);
        request.setAttribute("lesson", lesson);
        request.setAttribute("location", location);


        //update jsp
        UserSignInHelper helper = new UserSignInHelper();
        List<UserLesson> userLessons = helper.getUserLessons(user.getId(), 2);
        session.setAttribute("userLessons", userLessons);

        //create notification for student

        //create notification for instructor

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookingConfirmation.jsp");
        dispatcher.forward(request, response);

    }

    public void insertLesson(Lesson lesson) {

        GenericDao<Lesson> lessonDao = new GenericDao<>(Lesson.class);
        lessonDao.insert(lesson);
    }

    public Location getLocation(int locationId) {

        GenericDao<Location> locationDao = new GenericDao<>(Location.class);
        return locationDao.getById(locationId);

    }

    public User getInstructor(int instructorId) {

        GenericDao<User> userDao = new GenericDao<>(User.class);

        return userDao.getById(instructorId);
    }


    public void insertUserLesson(User user, Lesson lesson, Role role) {

        GenericDao<UserLesson> userLessonDao = new GenericDao<>(UserLesson.class);
        userLessonDao.insertManyToMany(new UserLesson(user, lesson, role));

    }

    public Role getUserRole(int roleId) {

        GenericDao<Role> roleDao = new GenericDao<Role>(Role.class);

        return roleDao.getById(roleId);

    }
}
