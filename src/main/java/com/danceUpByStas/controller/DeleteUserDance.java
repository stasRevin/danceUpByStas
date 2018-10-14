package com.danceUpByStas.controller;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserDance;
import com.danceUpByStas.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "DeleteUserDance",
            urlPatterns = {"/deleteUserDance"})
public class DeleteUserDance extends HttpServlet {
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String danceToDelete = request.getParameter("name");
        int userId = user.getId();

        //getElementsByEntitiesAndProperties(Map<String, Map<String, String>> entities)

        GenericDao<UserDance> userDanceDao = new GenericDao<>(UserDance.class);

        List<UserDance> userDanceList = (List<UserDance>) session.getAttribute("userDances");

        UserDance userDanceToDelete = userDanceList.stream().filter(d -> d.getDance().getName().equals(danceToDelete)).findFirst().get();
        userDanceDao.delete(userDanceToDelete);
        List<UserDance> userDances = userDanceDao.getElementsOfTypeAByIdOfEntityOfTypeB("user", userId);
        session.setAttribute("userDances", userDances);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doDelete(request, response);
    }
}
