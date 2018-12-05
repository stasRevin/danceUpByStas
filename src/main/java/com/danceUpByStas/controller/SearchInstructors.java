package com.danceUpByStas.controller;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.utilities.UserPhotoManager;
import com.danceUpByStas.utilities.UserSignInHelper;
import com.danceUpByStas.utilities.ZipCodeRadius;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

/**
 * This is the SearchInstructors servlet class designed to facilitate the search of the instructors by students.
 * @author srevin
 */
@WebServlet(name = "SearchInstructors",
            urlPatterns = {"/searchInstructors"})
public class SearchInstructors extends HttpServlet {

    /**
     * The doGet method designed to accept GET requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        ServletContext context = request.getServletContext();

        UserPhotoManager photoManager = new UserPhotoManager();
        UserSignInHelper helper = new UserSignInHelper();
        User user = (User) session.getAttribute("user");

        String zipCodeInput = request.getParameter("zipCode");
        String radiusInput = request.getParameter("radius");

        String zipCode = Objects.isNull(zipCodeInput) || zipCodeInput.isEmpty() ? user.getPostalCode() : zipCodeInput;
        String radius = Objects.isNull(radiusInput) || radiusInput.isEmpty() ? "5" : radiusInput;

        Set<User> instructors = getNearbyInstructors(zipCode, radius);

        request.setAttribute("usersFound", instructors);
        helper.setUserDances(instructors);
        photoManager.prepareFoundUsersPhotos(user.getId() + "", instructors, context);
        String url = "/instructorsFound.jsp";

        forward(request, response, url);

    }

    /**
     * This method forwards request and response to the specified page.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    private void forward(HttpServletRequest request, HttpServletResponse response, String url) throws  ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * This method returns the instructors who teach in the area near the user's address.
     * @param zipCode The user's postal code.
     * @param radius The radius of search.
     * @return instructors The set of instructors found.
     */
    public Set<User> getNearbyInstructors(String zipCode, String radius) {

        ZipCodeRadius zipCodeRadius = new ZipCodeRadius();
        return zipCodeRadius.getInstructorsWhoTeachNearMe(zipCode, radius);

    }

}
