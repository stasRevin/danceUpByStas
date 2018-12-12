package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Notification;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserLesson;
import com.danceUpByStas.enums.UserRoleEnum;
import com.danceUpByStas.utilities.UserSignInHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserViewProfileForward",
            urlPatterns = "/userViewProfileForward")
public class UserViewProfileForward extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        Integer role = (Integer) session.getAttribute("role");
        String url = "";

        UserSignInHelper signInHelper = new UserSignInHelper();

        List<Notification> notifications = signInHelper.getNotifications(user);
        List<UserLesson> userLessons = signInHelper.getUserLessons(user.getId(), role);

        session.setAttribute("notifications",
                notifications);
        session.setAttribute("userLessons", userLessons);

        if (role == UserRoleEnum.INSTRUCTOR.getRoleNumber()) {

            url = "/instructorViewProfile.jsp";

        } else if (role == UserRoleEnum.STUDENT.getRoleNumber()) {

            url = "/studentViewProfile.jsp";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
