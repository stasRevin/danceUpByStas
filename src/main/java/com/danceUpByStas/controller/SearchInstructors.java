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

@WebServlet(name = "SearchInstructors",
            urlPatterns = {"/searchInstructors"})
public class SearchInstructors extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        ServletContext context = request.getServletContext();

        UserPhotoManager photoManager = new UserPhotoManager();
        UserSignInHelper helper = new UserSignInHelper();
        User user = (User) session.getAttribute("user");
        String searchPhotosDirectoryPrefix = "";

        if (Objects.isNull(user)) {

            searchPhotosDirectoryPrefix = "";
        }


        String zipCodeInput = request.getParameter("zipCode");
        String radiusInput = request.getParameter("radius");

        String zipCode = Objects.isNull(zipCodeInput) || zipCodeInput.isEmpty() ? user.getPostalCode() : zipCodeInput;
        String radius = Objects.isNull(radiusInput) || radiusInput.isEmpty() ? "5" : radiusInput;

        Set<User> instructors = getNearbyInstructors(zipCode, radius);

        request.setAttribute("usersFound", instructors);
        helper.setUserDances(instructors);
        photoManager.prepareFoundUsersPhotos(user.getId() + "", instructors, context);

        forward(request, response);

        //clearUserPhotosFolder(instructors);

    }


    private void forward(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/instructorsFound.jsp");
        dispatcher.forward(request, response);
    }

    public Set<User> getNearbyInstructors(String zipCode, String radius) {

        ZipCodeRadius zipCodeRadius = new ZipCodeRadius();

        return zipCodeRadius.getInstructorsWhoTeachNearMe(zipCode, radius);

    }



}
