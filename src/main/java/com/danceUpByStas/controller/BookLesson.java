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

public class BookLesson extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        HttpSession session = request.getSession(false);
        String instructorIdInput = request.getParameter("instructorId");
        String staticImagePath = (String) context.getAttribute("staticImagePath");
        UserSignInHelper helper = new UserSignInHelper();
        User loggedInUser = (User)session.getAttribute("user");

        int instructorId = Integer.parseInt(instructorIdInput);
        //get instructor
        User instructor = getInstructor(instructorId);

        //get instructor dances
        Set<User> instructors = new HashSet<>();
        instructors.add(instructor);
        helper.setUserDances(instructors);

        //get instructor availability
        List<Schedule> schedules = helper.getUserSchedule(instructorId);
        //set instructor in request
        request.setAttribute("instructor", instructor);
        request.setAttribute("schedules", schedules);
        //forward to bookInstructor.jsp
        String userPhotoDirectory = (String) session.getAttribute("userPhotoDirectory");

        if (!checkIfInstructorPhotoExists(staticImagePath + File.separator + userPhotoDirectory, instructor.getPhotoName())) {

            UserPhotoManager photoManager = new UserPhotoManager();
            photoManager.prepareFoundUsersPhotos(loggedInUser.getId() + "", new HashSet<User>(Arrays.asList(instructor)), context);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookInstructor.jsp");
        dispatcher.forward(request, response);

    }

    private boolean checkIfInstructorPhotoExists(String userPhotoDirectory, String photoName) {

        File photoFile = new File(userPhotoDirectory + File.separator + photoName);

        if (photoFile.exists() && !photoFile.isDirectory()) {

            return  true;
        }

        return false;
    }


    private User getInstructor(int userId) {

        GenericDao<User> userDao = new GenericDao<>(User.class);

        User instructor = userDao.getById(userId);

        return instructor;
    }
}
