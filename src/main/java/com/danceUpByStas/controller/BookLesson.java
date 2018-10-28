package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Schedule;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.UserSignInHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "BookLesson",
            urlPatterns = {"/bookLesson"})

public class BookLesson extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String instructorIdInput = request.getParameter("instructorId");
        UserSignInHelper helper = new UserSignInHelper();

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
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookInstructor.jsp");
        dispatcher.forward(request, response);

    }


    private User getInstructor(int userId) {

        GenericDao<User> userDao = new GenericDao<>(User.class);

        User instructor = userDao.getById(userId);

        return instructor;
    }
}
