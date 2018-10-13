package com.danceUpByStas.controller;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.UserPhotoManager;
import org.apache.catalina.realm.RealmBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "UserProfileUpdate",
            urlPatterns = {"/updateUserProfile"})

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 100,
        maxRequestSize = 1024 * 1024 * 500
)
public class UserProfileUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");
        int userId = user.getId();
        String userPhotoFile = (String)getServletContext().getAttribute("profilePhotoPath");

        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipCode = request.getParameter("zip");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");
        String hashedPassword = "";
        GenericDao<User> userDao = new GenericDao<>(User.class);

        if (!password.isEmpty() && password.equals(passwordConfirmation)) {

            hashedPassword = RealmBase.Digest(password, "SHA-256", "UTF-8");
            user.setPassword(hashedPassword);

        }

        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddressOne(address1);
        user.setAddressTwo(address2);
        user.setCity(city);
        user.setState(state);
        user.setPostalCode(zipCode);

        File userFolder = new File(userPhotoFile + File.separator + userId);



        userDao.saveOrUpdate(user);
        String photoName = request.getPart("profilePhoto").getName();
        long size = request.getPart("profilePhoto").getSize();

        if (request.getPart("profilePhoto").getSize() > 0) {
            UserPhotoManager photoManager = new UserPhotoManager();
            photoManager.saveUserPhoto(request, userFolder, user, userDao);
        }


        String url = "";

        if ((Integer)session.getAttribute("role") == 1) {

            url = "/danceup/signInInstructor";
        }

        response.sendRedirect(url);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
