package com.danceUpByStas.controller;

import com.danceUpByStas.entity.*;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(
        name = "com.danceUpByStas.controller.InstructorSignIn",
        urlPatterns = {"/signInInstructor"}

)
public class InstructorSignIn extends HttpServlet {
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

            List<UserDance> dances = user.getDances();
            GenericDao<UserDance> userDanceDao = new GenericDao<>(UserDance.class);
            GenericDao<Schedule> scheduleGenericDao = new GenericDao<>(Schedule.class);
            GenericDao<UserLesson> userLessonDao = new GenericDao<>(UserLesson.class);

            List<UserDance> userDances = userDanceDao.getElementsOfTypeAByIdOfEntityOfTypeB("user", userId);
            List<Schedule> schedules = scheduleGenericDao.getElementsOfTypeAByIdOfEntityOfTypeB("user", userId);

            Map<String, Map<String, String>> userLessonEntities = new HashMap<>();
            Map<String, String> userLessonPropertiesOne = new HashMap<>();
            Map<String, String> userLessonPropertiesTwo = new HashMap<>();

            userLessonPropertiesOne.put("id", userId + "");
            userLessonPropertiesTwo.put("id", "1");
            userLessonEntities.put("user", userLessonPropertiesOne);
            userLessonEntities.put("role", userLessonPropertiesTwo);

            List<UserLesson> userLessons = userLessonDao.getElementsByEntitiesAndProperties(userLessonEntities);

            long lessonsTaughtCount = userLessons.stream().filter(l -> l.getLesson().getDate().isBefore(LocalDate.now())).count();

            session.setAttribute("user", user);
            session.setAttribute("userDances", userDances);
            session.setAttribute("schedules", schedules);
            session.setAttribute("userLessons", userLessons);
            session.setAttribute("lessonsTaughtCount", lessonsTaughtCount);
            String userPhotoPath = (String)context.getAttribute("profilePhotoPath")
                                + File.separator + userId + File.separator + user.getPhotoName();
            session.setAttribute("userPhotoPath", userPhotoPath);

            UserPhotoManager photoManager = new UserPhotoManager();
            photoManager.prepareUserPhoto(userPhotoPath, photoName);
            url = "/instructor/instructorViewProfile.jsp";

        } else {

            url = "/generalError.jsp";
        }

        RequestDispatcher dispatcher = context.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);

    }



}
