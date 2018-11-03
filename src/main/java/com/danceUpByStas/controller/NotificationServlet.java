package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Notification;
import com.danceUpByStas.entity.User;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "NotificationServlet",
            urlPatterns = {"/notifications"})
public class NotificationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger logger = LogManager.getLogger(this.getClass());
        HttpSession session = request.getSession(false);
        UserSignInHelper signInHelper = new UserSignInHelper();
        User user = (User) session.getAttribute("user");

        List<Notification> notifications = (List<Notification>) session.getAttribute("notifications");

        //Mark notifications as read
        notifications = signInHelper.getNotifications(user);

        for (Notification notification : notifications) {

            logger.debug("2. notification is read value: {}", notification.getIsRead());
        }

        request.setAttribute("notificationRequest", notifications);
        markAsRead(notifications);
        notifications = signInHelper.getNotifications(user);
        session.setAttribute("notifications", notifications);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/notifications.jsp");
        dispatcher.forward(request, response);
    }



    public void markAsRead(List<Notification> notifications) {

        GenericDao<Notification> notificationDao = new GenericDao<>(Notification.class);
        Logger logger = LogManager.getLogger(this.getClass());

        for (Notification notification : notifications) {

            notification.setIsRead(1);
            logger.debug("1. notification is read value: {}", notification.getIsRead());
            notificationDao.saveOrUpdate(notification);

        }

    }
}
