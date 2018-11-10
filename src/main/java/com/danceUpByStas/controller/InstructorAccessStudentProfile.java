package com.danceUpByStas.controller;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserDance;
import com.danceUpByStas.utilities.UserPhotoManager;
import com.danceUpByStas.utilities.UserSignInHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "InstructorAccessStudentProfile",
            urlPatterns = {"/instructorAccessStudentProfile"})

public class InstructorAccessStudentProfile extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger logger = LogManager.getLogger(this.getClass());
        ServletContext context = getServletContext();
        HttpSession session = request.getSession(false);
        UserPhotoManager photoManager = new UserPhotoManager();
        UserSignInHelper signInHelper = new UserSignInHelper();
        User instructor = (User) session.getAttribute("user");
        String studentId = (String) request.getParameter("studentId");
        User student = signInHelper.getUserById(Integer.parseInt(studentId));
        String staticImagePath = (String) context.getAttribute("staticImagePath");
        String userPhotoDirectory = (String) session.getAttribute("userPhotoDirectory");

        Set<User> students = new HashSet<>();
        students.add(student);
        signInHelper.setUserDances(students);

        for (UserDance studentDance : student.getDances()) {

            logger.debug("Student Dance: {}", studentDance.getDance().getName());

        }

        if (!photoManager.checkIfUserPhotoExists(staticImagePath + File.separator + userPhotoDirectory, student.getPhotoName())) {

            logger.debug("Student {} photo {} was not found, preparing the photo...", student.getFirstName(), student.getPhotoName());
            photoManager.prepareFoundUsersPhotos(instructor.getId() + "", new HashSet<User>(Arrays.asList(student)), context);
        }

        request.setAttribute("student", student);

        RequestDispatcher dispatcher = context.getRequestDispatcher("/instructorAccessStudentProfile.jsp");
        dispatcher.forward(request, response);

    }
}
