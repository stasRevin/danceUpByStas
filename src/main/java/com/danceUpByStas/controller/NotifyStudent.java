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

@WebServlet(name = "NotifyStudent",
            urlPatterns = {"/notifyStudent"})

public class NotifyStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger logger = LogManager.getLogger(this.getClass());

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        String message = (String) request.getParameter("message");
        String studentId = (String) request.getParameter("studentId");

        UserSignInHelper signInHelper = new UserSignInHelper();
        User student = signInHelper.getUserById(Integer.parseInt(studentId));

        GenericDao<Notification> notificationDao = new GenericDao<>(Notification.class);
        Notification notification = new Notification("Notification from instructor " + user.getFirstName()
                + " " + user.getLastName() + ": " + message, student, 0);

        notificationDao.insert(notification);

        Set<User> students = new HashSet<>();
        students.add(student);
        signInHelper.setUserDances(students);

        request.setAttribute("student", student);

        logger.debug("Notify user servlet: student name: {} " + student.getFirstName());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/instructorAccessStudentProfile.jsp");
        dispatcher.forward(request, response);

    }

}
