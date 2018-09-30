package com.danceUpByStas.controller;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "UserSignUp",
        urlPatterns = "/userSignUp")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 100,
        maxRequestSize = 1024 * 1024 * 500
)

public class UserSignUp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Get session
        HttpSession session = request.getSession();

        //Get parameters from the form
        String role = request.getParameter("role");
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipCode = request.getParameter("zip");
        String password = request.getParameter("password");

        //Create new User
        User user = new User(username, password, 0, firstName, lastName, address1, address2, city, state, zipCode, 0.00, "");
        //Insert new User
        GenericDao<User> userDao = new GenericDao<User>(User.class);
        int userId = userDao.insert(user);
        //Associate user with the role

        //Forward to the login page


        response.sendRedirect("/signIn.jsp");
    }


}
