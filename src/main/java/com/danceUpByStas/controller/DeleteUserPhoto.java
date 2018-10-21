package com.danceUpByStas.controller;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.utilities.UserPhotoManager;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(name = "DeleteUserPhoto",
            urlPatterns = {"/deleteUserPhoto"})
public class DeleteUserPhoto extends HttpServlet {


    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user =  (User) session.getAttribute("user");
        String userPhotoPathValue = (String)getServletContext().getAttribute("profilePhotoPath")
                + File.separator + user.getId() + File.separator + user.getPhotoName();

        Path photoPath = Paths.get(userPhotoPathValue);

        UserPhotoManager photoManager = new UserPhotoManager();

        photoManager.deleteUserPhoto(user, photoPath);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doDelete(request, response);
    }
}
