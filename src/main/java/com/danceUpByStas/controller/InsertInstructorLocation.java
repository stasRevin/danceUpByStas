package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Location;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "InsertInstructorLocation",
            urlPatterns = {"/insertInstructorLocation"})
public class InsertInstructorLocation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        String locationId = request.getParameter("id");

        GenericDao<Location> locationDao = new GenericDao<>(Location.class);
        GenericDao<User> userDao = new GenericDao<>(User.class);

        Location location = locationDao.getById(Integer.parseInt(locationId));
        User user = (User) session.getAttribute("user");
        user.addTeachingLocation(location);

        userDao.saveOrUpdate(user);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/updateInstructorLocations");
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}
