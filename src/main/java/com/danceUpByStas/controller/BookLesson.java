package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Schedule;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.UserPhotoManager;
import com.danceUpByStas.utilities.UserSignInHelper;

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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "BookLesson",
            urlPatterns = {"/bookLesson"})

/**
 * This is the BookLesson servlet class designed to facilitate the booking of a dance lesson.
 * @author srevin
 */

public class BookLesson extends HttpServlet {

    /**
     * This method responds to the GET requests.
     * @param request The instance of HTTP request
     * @param response The instance of HTTP response
     * @throws ServletException The servlet exception.
     * @throws IOException The input/output exception.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        HttpSession session = request.getSession(false);
        String instructorIdInput = request.getParameter("instructorId");
        String staticImagePath = (String) context.getAttribute("staticImagePath");
        UserSignInHelper helper = new UserSignInHelper();
        UserPhotoManager photoManager = new UserPhotoManager();
        User loggedInUser = (User)session.getAttribute("user");

        int instructorId = Integer.parseInt(instructorIdInput);
        User instructor = helper.getUserById(instructorId);

        Set<User> instructors = new HashSet<>();
        instructors.add(instructor);
        helper.setUserDances(instructors);

        List<Schedule> schedules = helper.getUserSchedule(instructorId);
        request.setAttribute("instructor", instructor);
        request.setAttribute("schedules", schedules);
        String userPhotoDirectory = (String) session.getAttribute("userPhotoDirectory");

        if (!photoManager.checkIfUserPhotoExists(staticImagePath + File.separator
                + userPhotoDirectory, instructor.getPhotoName())) {

            photoManager.prepareFoundUsersPhotos(loggedInUser.getId() + "", instructors, context);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookInstructor.jsp");
        dispatcher.forward(request, response);

    }

}
