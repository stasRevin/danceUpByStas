package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Location;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.UserPhotoManager;
import com.danceUpByStas.utilities.ZipCodeRadius;
import com.zipwise.DataList;
import com.zipwise.DataListItem;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "SearchInstructors",
            urlPatterns = {"/searchInstructors"})
public class SearchInstructors extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        GenericDao<Location> locationDao = new GenericDao<>(Location.class);

        User user = (User) session.getAttribute("user");

        String zipCode = request.getParameter("zipCode");
        String radius = request.getParameter("radius");

        Set<User> instructors = getNearbyInstructors(zipCode, radius);

        request.setAttribute("usersFound", instructors);

        prepareInstructorPhotos(instructors);

        forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        GenericDao<Location> locationDao = new GenericDao<>(Location.class);

        User user = (User) session.getAttribute("user");
        Set<User> instructors = getNearbyInstructors(user.getPostalCode(), "5");

        request.setAttribute("usersFound", instructors);

        prepareInstructorPhotos(instructors);

        forward(request, response);

    }


    private void forward(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/instructorsFound.jsp");
        dispatcher.forward(request, response);
    }

    private Set<User> getNearbyInstructors(String zipCode, String radius) {

        ZipCodeRadius zipCodeRadius = new ZipCodeRadius();

        return zipCodeRadius.getInstructorsWhoTeachNearMe(zipCode, radius);

    }

    private void prepareInstructorPhotos(Set<User> instructors) {

        ServletContext context = getServletContext();
        UserPhotoManager manager = new UserPhotoManager();

        String userPhotoPath = "";
        String photoName = "";

        for (User instructor : instructors) {

            photoName = instructor.getPhotoName();
            userPhotoPath = (String)context.getAttribute("profilePhotoPath")
                    + File.separator + instructor.getId() + File.separator + photoName;
            manager.prepareUserPhoto(userPhotoPath, photoName);
        }

    }
}
