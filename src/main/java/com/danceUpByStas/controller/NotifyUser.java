package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Notification;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.enums.UserRoleEnum;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.UserSignInHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the NotifyUser servlet class designed to facilitate the sending of a notification message
 * from one user to another.
 * @author srevin
 */
@WebServlet(name = "NotifyUser",
            urlPatterns = {"/notifyUser"})
public class NotifyUser extends HttpServlet {

    /**
     * This method handles the POST requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger logger = LogManager.getLogger(this.getClass());

        HttpSession session = request.getSession(false);
        User loggedInUser = (User) session.getAttribute("user");
        Integer role = (Integer) session.getAttribute("role");
        String url = "";

        String message = request.getParameter("message");
        String userId = request.getParameter("recepientUserId");

        UserSignInHelper signInHelper = new UserSignInHelper();
        User recepientUser = signInHelper.getUserById(Integer.parseInt(userId));

        GenericDao<Notification> notificationDao = new GenericDao<>(Notification.class);
        Notification notification = new Notification("Notification from user " + loggedInUser.getFirstName()
                + " " + loggedInUser.getLastName() + ": " + message, recepientUser, 0, LocalDateTime.now());

        notificationDao.insert(notification);

        Set<User> users = new HashSet<>();
        users.add(recepientUser);
        signInHelper.setUserDances(users);

        if (role == UserRoleEnum.INSTRUCTOR.getRoleNumber()) {

            url = "/instructorAccessStudentProfile.jsp";
            request.setAttribute("student", recepientUser);

        } else if (role == UserRoleEnum.STUDENT.getRoleNumber()) {

            url = "/bookInstructor.jsp";
            request.setAttribute("instructor", recepientUser);
        }

        logger.debug("Notify user servlet: student name: {} " + recepientUser.getFirstName());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
