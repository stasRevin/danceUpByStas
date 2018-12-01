package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Location;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This is the DeleteInstructorLocation servlet class designed to facilitate the dissociation of instructor's teaching
 * location for the instructor's profile.
 * @author srevin
 */
@WebServlet(name = "DeleteInstructorLocation",
            urlPatterns = {"/deleteInstructorLocation"})
public class DeleteInstructorLocation extends HttpServlet {

    /**
     * This method responds to the DELETE requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input/output exception.
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String locationId = request.getParameter("id");

        GenericDao<Location> locationDao = new GenericDao<>(Location.class);
        GenericDao<User> userDao = new GenericDao<>(User.class);

        Location location = locationDao.getById(Integer.parseInt(locationId));

        user.removeTeachingLocation(location);
        userDao.saveOrUpdate(user);

        user = userDao.getById(user.getId());

        session.setAttribute("user", user);

    }

    /**
     * This method responds to the GET requests.
     * @param request The instance of HTTP request
     * @param response The instance of HTTP response
     * @throws ServletException The servlet exception.
     * @throws IOException The input/output exception.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doDelete(request, response);
    }
}
