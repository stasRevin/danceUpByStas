package com.danceUpByStas.controller;

import com.danceUpByStas.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This is the UserLogOut servlet class designed to facilitate the log out process of the users.
 * @author srevin
 */
@WebServlet(name = "UserLogOut",
            urlPatterns = {"/userLogOut"})
public class UserLogOut extends HttpServlet {

    /**
     * The doGet method designed to accept GET requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if ((User)session.getAttribute("user") != null) {

            session.invalidate();
        }

        response.sendRedirect("/danceup/logOutConfirmation.jsp");
    }
}
