package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Location;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.InputValidator;
import com.danceUpByStas.utilities.UserSignInHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

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
        InputValidator inputValidator = new InputValidator();

        Properties properties = inputValidator.loadProperties("/regexValidation.properties");
        GenericDao<Location> locationDao = new GenericDao<>(Location.class);
        GenericDao<User> userDao = new GenericDao<>(User.class);
        Location location = null;

        if (inputValidator.validateMatchesRegularExpression(locationId, properties.getProperty("regex.numeric"))) {

            location = locationDao.getById(Integer.parseInt(locationId));
        }
        user.removeTeachingLocation(location);
        userDao.saveOrUpdate(user);

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
