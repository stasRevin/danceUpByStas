package com.danceUpByStas.controller;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserDance;
import com.danceUpByStas.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        String danceToDelete = request.getParameter("name");
        int userId = user.getId();
        Logger logger = LogManager.getLogger(this.getClass());

        //getElementsByEntitiesAndProperties(Map<String, Map<String, String>> entities)

        List<UserDance> userDanceList = (List<UserDance>) session.getAttribute("userDances");

        List<UserDance> userDances = deleteUserDance(userDanceList, danceToDelete, userId);

        for (UserDance dance : userDances) {

            logger.debug("After removal, remaining dance in userDance: {}", dance.getDance().getName());

        }
        session.setAttribute("userDances", userDances);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doDelete(request, response);
    }

    public List<UserDance> deleteUserDance(List<UserDance> userDanceList, String danceToDelete, int userId) {

        GenericDao<UserDance> userDanceDao = new GenericDao<>(UserDance.class);
        UserDance userDanceToDelete = userDanceList.stream().filter(d -> d.getDance().getName().equals(danceToDelete))
                                                   .findFirst().get();

        userDanceDao.delete(userDanceToDelete);
        return userDanceDao.getElementsOfTypeAByIdOfEntityOfTypeB("user", userId);

    }
}
