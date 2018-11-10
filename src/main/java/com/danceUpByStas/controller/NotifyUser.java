package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Notification;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.UserSignInHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "NotifyUser",
            urlPatterns = {"/notifyUser"})

public class NotifyUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger logger = LogManager.getLogger(this.getClass());

        HttpSession session = request.getSession(false);
        User loggedInUser = (User) session.getAttribute("user");
        Integer role = (Integer) session.getAttribute("role");
        String url = "";

        String message = (String) request.getParameter("message");
        String userId = (String) request.getParameter("recepientUserId");

        UserSignInHelper signInHelper = new UserSignInHelper();
        User recepientUser = signInHelper.getUserById(Integer.parseInt(userId));

        GenericDao<Notification> notificationDao = new GenericDao<>(Notification.class);
        Notification notification = new Notification("Notification from user " + loggedInUser.getFirstName()
                + " " + loggedInUser.getLastName() + ": " + message, recepientUser, 0);

        notificationDao.insert(notification);

        Set<User> users = new HashSet<>();
        users.add(recepientUser);
        signInHelper.setUserDances(users);

        if (role == 1) {

            url = "/instructorAccessStudentProfile.jsp";
            request.setAttribute("student", recepientUser);

        } else if (role == 2) {

            url = "/bookInstructor.jsp";
            request.setAttribute("instructor", recepientUser);
        }

        logger.debug("Notify user servlet: student name: {} " + recepientUser.getFirstName());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
