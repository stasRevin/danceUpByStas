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
import java.util.*;

/**
 * This is the NotificationServlet class designed to load notification related information
 * to the notification page.
 * @author srevin
 */
@WebServlet(name = "NotificationServlet",
            urlPatterns = {"/notifications"})
public class NotificationServlet extends HttpServlet {

    /**
     * The doGet method designed to accept GET requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        UserSignInHelper signInHelper = new UserSignInHelper();
        User user = (User) session.getAttribute("user");

        List<Notification> notifications = (List<Notification>) session.getAttribute("notifications");

        notifications = signInHelper.getNotifications(user);

        if (!Objects.isNull(notifications) && notifications.size() > 0) {

            request.setAttribute("notificationRequest", notifications);
            markAsRead(notifications);
            notifications = signInHelper.getNotifications(user);
            session.setAttribute("notifications", notifications);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/notifications.jsp");
        dispatcher.forward(request, response);
    }


    /**
     * This methods marks notifications as read.
     * @param notifications The list of notifications to mark.
     */
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
