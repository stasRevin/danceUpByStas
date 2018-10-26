package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Location;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import com.zipwise.DataList;
import com.zipwise.DataListItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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


       // ZipCodeRadius zipCodeRadius = new ZipCodeRadius();

        //List<String> nearbyZipCodes = zipCodeRadius.getNearbyZipCodes(zipCode, radius);

        //Set<Location> locationsFound = locationDao.getElementByProperty("postalCode", );


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
