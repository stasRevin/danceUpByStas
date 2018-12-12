package com.danceUpByStas.controller;

import com.danceUpByStas.enums.UserRoleEnum;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * This is the UserProfileUpdateForward servlet class designed to facilitate the forward operation to
 * the servlet where user profile update will take place.
 * @author srevin
 */
@WebServlet(name = "UserProfileUpdateForward",
            urlPatterns = {"/userProfileUpdateForward"})
public class UserProfileUpdateForward extends HttpServlet {

    /**
     * This method handles the POST requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Integer role = (Integer) session.getAttribute("role");
        String url = "";

        if (!Objects.isNull(role)) {

            forward("/userProfileUpdate.jsp", request, response);

        } else {

            if (role == UserRoleEnum.INSTRUCTOR.getRoleNumber()) {

                forward("/signInInstructor", request, response);

            } else if (role == UserRoleEnum.STUDENT.getRoleNumber()) {

                forward("/signInStudent", request, response);
            }
        }


    }

    /**
     * The doGet method designed to accept GET requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    /**
     * This method will forward user to the specified location.
     * @param url The uniform resource locator where user will be forwarded.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input/output exception.
     */
    private void forward(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
