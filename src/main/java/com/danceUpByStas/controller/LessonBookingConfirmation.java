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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * This is the LessonBookingConfirmation servlet class designed to facilitate the booking of a dance lesson with
 * an instructor by a student.
 * @srevin
 */
@WebServlet(name = "LessonBookingConfirmation",
            urlPatterns= {"/bookingConfirmation"})

public class LessonBookingConfirmation extends HttpServlet {

    /**
     * This method handles the POST requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
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
        session.setAttribute("unreadNotifications", "true");

        //create notification for student
        createNotification(lesson, instructor, user);
        //create notification for instructor
        createNotification(lesson, user, instructor);

        //Get a list of unread notifications
        List<Notification> notifications = helper.getNotifications(user);

        //Set into the session
        session.setAttribute("notifications", notifications);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookingConfirmation.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * This metods inserts the lesson into database.
     * @param lesson The lesson to insert.
     */
    private void insertLesson(Lesson lesson) {

        GenericDao<Lesson> lessonDao = new GenericDao<>(Lesson.class);
        lessonDao.insert(lesson);
    }

    /**
     * This method returns instructor's teaching location.
     * @param locationId The identification number of the location.
     * @return location The instructor's teaching location.
     */
    private Location getLocation(int locationId) {

        GenericDao<Location> locationDao = new GenericDao<>(Location.class);
        return locationDao.getById(locationId);

    }

    /**
     * This method returns the instructor entity based on the supplied id.
     * @param instructorId The instructor's identification number.
     * @return instructor The instructor entity object.
     */
    private User getInstructor(int instructorId) {

        GenericDao<User> userDao = new GenericDao<>(User.class);
        return userDao.getById(instructorId);
    }

    /**
     * This method inserts the UserLesson entity into the database.
     * @param user
     * @param lesson
     * @param role
     */
    private void insertUserLesson(User user, Lesson lesson, Role role) {

        GenericDao<UserLesson> userLessonDao = new GenericDao<>(UserLesson.class);
        userLessonDao.insertManyToMany(new UserLesson(user, lesson, role));

    }

    /**
     * This method returns the user role.
     * @param roleId The identification number of the role.
     * @return role The role entity.
     */
    private Role getUserRole(int roleId) {

        GenericDao<Role> roleDao = new GenericDao<Role>(Role.class);

        return roleDao.getById(roleId);

    }

    /**
     * This method creates a notification for a user.
     * @param lesson The dance lesson.
     * @param withUserInMessage The user being mentioned in the message.
     * @param notificationOwner The owner of the notification.
     */
    private void createNotification(Lesson lesson, User withUserInMessage, User notificationOwner) {

        GenericDao<Notification> notificationDao = new GenericDao<>(Notification.class);
        String message = "A lesson was scheduled with " + withUserInMessage.getFirstName() + " " + withUserInMessage.getLastName();
        message += " at " + lesson.getLocation().getName() + " on " + lesson.getDate() + " at " + lesson.getStartTime() + ".";
        Notification notification = new Notification(message, notificationOwner, 0, LocalDateTime.now());
        notificationDao.insert(notification);

    }
}
