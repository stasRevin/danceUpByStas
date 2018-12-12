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

@WebServlet(name = "UserProfileUpdateForward",
            urlPatterns = {"/userProfileUpdateForward"})


public class UserProfileUpdateForward extends HttpServlet {

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }


    private void forward(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
