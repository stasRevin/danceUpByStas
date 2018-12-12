package com.danceUpByStas.controller;

import com.danceUpByStas.entity.*;
import com.danceUpByStas.enums.UserRoleEnum;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.SimpleVisitor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.danceUpByStas.utilities.UserPhotoManager;
import com.danceUpByStas.utilities.UserSignInHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is the InstructorSignIn servlet class design to facilitate the sign in process of the instructors.
 * @author srevin
 */
@WebServlet(
        name = "com.danceUpByStas.controller.InstructorSignIn",
        urlPatterns = {"/signInInstructor"}

)
public class InstructorSignIn extends HttpServlet {

    /**
     * This method handles the POST requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        HttpSession session = request.getSession();

        String username = request.getUserPrincipal().getName();
        String url = "";
        GenericDao<User> userDao = new GenericDao<>(User.class);

        User user = userDao.getElementByProperty("username", username);

        if (Objects.nonNull(user)) {

            int userId = user.getId();
            String photoName = user.getPhotoName();

            UserSignInHelper signInHelper = new UserSignInHelper();

            List<UserDance> userDances = signInHelper.getUserDances(userId);
            List<Schedule> schedules = signInHelper.getUserSchedule(userId);
            List<UserLesson> userLessons = signInHelper.getUserLessons(userId, UserRoleEnum.INSTRUCTOR.getRoleNumber());
            userLessons = signInHelper.getUsersForLessons(userLessons, UserRoleEnum.STUDENT.getRoleNumber());
            List<Notification> notifications = signInHelper.getNotifications(user);

            long lessonsTaughtCount = userLessons.stream().filter(l -> l.getLesson().getDate().isBefore(LocalDate.now())).count();

            session.setAttribute("notifications", notifications);
            session.setAttribute("user", user);
            session.setAttribute("role", UserRoleEnum.INSTRUCTOR.getRoleNumber());
            session.setAttribute("userDances", userDances);
            session.setAttribute("schedules", schedules);
            session.setAttribute("userLessons", userLessons);
            session.setAttribute("lessonsTaughtCount", lessonsTaughtCount);
            String userPhotoPath = (String)context.getAttribute("profilePhotoPath")
                                + File.separator + userId + File.separator + user.getPhotoName();
            String staticUserPhotoPath = (String)context.getAttribute("staticUserPhotoPath") + userId;

            String usersFoundPhotosPath = (String)context.getAttribute("usersFoundPhotosPath") + userId;

            String photoDirectoryName = (String)context.getAttribute("photoDirectoryName") + userId;
            session.setAttribute("userPhotoPath", userPhotoPath);
            session.setAttribute("usersFoundPhotosPath", usersFoundPhotosPath);
            session.setAttribute("userPhotoDirectory", staticUserPhotoPath);

            UserPhotoManager photoManager = new UserPhotoManager();
            photoManager.prepareUserPhoto(userPhotoPath, photoDirectoryName, photoName);
            url = "/instructorViewProfile.jsp";

        } else {

            url = "/generalError.jsp";
        }

        RequestDispatcher dispatcher = context.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    /**
     * The doGet method designed to accept GET requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);

    }


}
