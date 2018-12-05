package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Dance;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserDance;
import com.danceUpByStas.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * This is the InsertUserDance servlet class designed to facilitate the insertion of the UserDance entities.
 * @author srevin
 */
@WebServlet(name = "InsertUserDance",
            urlPatterns = {"/insertUserDance"}
)
public class InsertUserDance extends HttpServlet {

    /**
     * This method services the POST requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        String danceName = (String) request.getParameter("danceName");
        String experience = (String) request.getParameter("experience");
        String learningProficiency = (String) request.getParameter("learningProficiency");

        Integer role = (Integer) session.getAttribute("role");

        GenericDao<Dance> danceDao = new GenericDao<>(Dance.class);
        GenericDao<UserDance> userDanceDao = new GenericDao<>(UserDance.class);

        Dance dance = danceDao.getElementByProperty("name", danceName);
        User user = (User) session.getAttribute("user");

        UserDance userDance = new UserDance(user, dance);

        if (role == 1) {

            userDance.setYearsOfExperience(Integer.parseInt(experience));
        } else if (role == 2) {

            userDance.setLearningProficiency(Integer.parseInt(learningProficiency));

        }

        userDanceDao.insertManyToMany(userDance);

        List<UserDance> userDances = userDanceDao.getElementsOfTypeAByIdOfEntityOfTypeB("user", user.getId());

        session.setAttribute("userDances", userDances);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/updateUserDances");
        dispatcher.forward(request, response);
    }


    /**
     * This method responds to the GET requests.
     * @param request The instance of HTTP request
     * @param response The instance of HTTP response
     * @throws ServletException The servlet exception.
     * @throws IOException The input/output exception.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        doPost(request, response);
    }


}
