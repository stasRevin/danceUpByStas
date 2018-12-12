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

/**
 * This is the UpdateInstructorLocations servlet class designed to facilitate the update of the instructor teaching
 * locations.
 * @author srevin
 */
@WebServlet(name = "UpdateInstructorLocations",
            urlPatterns = {"/updateInstructorLocations"})
public class UpdateInstructorLocations extends HttpServlet {

    /**
     * The doGet method designed to accept GET requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
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

    /**
     * This method handles the POST requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        doGet(request, response);
    }
}
