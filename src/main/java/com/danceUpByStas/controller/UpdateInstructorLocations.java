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
import java.util.List;
import java.util.Set;

@WebServlet(name = "UpdateInstructorLocations",
            urlPatterns = {"/updateInstructorLocations"})
public class UpdateInstructorLocations extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("user");

        Set<Location> locations = user.getLocations();

        GenericDao<Location> locationDao = new GenericDao<>(Location.class);

        List<Location> allLocations = locationDao.getAll();

        allLocations.removeAll(locations);

        request.setAttribute("locations", locations);
        request.setAttribute("allLocations", allLocations);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/updateInstructorLocations.jsp");
        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        doGet(request, response);
    }
}
