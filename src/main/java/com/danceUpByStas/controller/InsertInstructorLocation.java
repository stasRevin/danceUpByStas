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

/**
 * This is the InsertInstructionLocation service designed to service requests to insert a teaching location for
 * instructors.
 * @author srevin
 */
@WebServlet(name = "InsertInstructorLocation",
            urlPatterns = {"/insertInstructorLocation"})
public class InsertInstructorLocation extends HttpServlet {

    /**
     * The doPost method is designed to service POST requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
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
