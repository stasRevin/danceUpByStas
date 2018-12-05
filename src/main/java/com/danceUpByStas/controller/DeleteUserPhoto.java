package com.danceUpByStas.controller;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.utilities.UserPhotoManager;

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

/**
 * This is DeleteUserPhoto servlet class designed to service request to delete a photo from the user profile.
 * @author srevin
 */
@WebServlet(name = "DeleteUserPhoto",
            urlPatterns = {"/deleteUserPhoto"})
public class DeleteUserPhoto extends HttpServlet {


    /**
     * The doDelete method facilitates the removal of the photo.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user =  (User) session.getAttribute("user");
        String userPhotoPathValue = (String)getServletContext().getAttribute("profilePhotoPath")
                + File.separator + user.getId() + File.separator + user.getPhotoName();

        Path photoPath = Paths.get(userPhotoPathValue);

        UserPhotoManager photoManager = new UserPhotoManager();

        photoManager.deleteUserPhoto(user, photoPath);
    }

    /**
     * The doGet method designed to accept GET requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doDelete(request, response);
    }
}
