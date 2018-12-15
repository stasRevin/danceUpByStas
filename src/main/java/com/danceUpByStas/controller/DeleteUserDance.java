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
import java.util.List;

/**
 * This is the DeleteUserDance servlet class designed to facilitate the removal of the UserDance instances.
 * @author srevin
 */

@WebServlet(name = "DeleteUserDance",
            urlPatterns = {"/deleteUserDance"})
public class DeleteUserDance extends HttpServlet {

    /**
     * This method responds to the DELETE requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input/output exception.
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        String danceToDelete = request.getParameter("name");
        int userId = user.getId();
        Logger logger = LogManager.getLogger(this.getClass());

        List<UserDance> userDanceList = (List<UserDance>) session.getAttribute("userDances");
        List<UserDance> userDances = deleteUserDance(userDanceList, danceToDelete, userId);

        for (UserDance dance : userDances) {

            logger.debug("After removal, remaining dance in userDance: {}", dance.getDance().getName());

        }
        session.setAttribute("userDances", userDances);
    }

    /**
     * This method responds to the GET requests.
     * @param request The instance of HTTP request
     * @param response The instance of HTTP response
     * @throws ServletException The servlet exception.
     * @throws IOException The input/output exception.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doDelete(request, response);
    }

    /**
     * This methods deletes the UserDance instance from the database.
     * @param userDanceList The list of UserDance instances.
     * @param danceToDelete The dance to delete.
     * @param userId The user id.
     * @return userDanceList The list of UserDance instances.
     */
    private List<UserDance> deleteUserDance(List<UserDance> userDanceList, String danceToDelete, int userId) {

        Logger logger = LogManager.getLogger(this.getClass());
        GenericDao<UserDance> userDanceDao = new GenericDao<>(UserDance.class);
        UserDance userDanceToDelete = userDanceList.stream().filter(d -> d.getDance().getName().equals(danceToDelete))
                                                   .findFirst().get();
        List<UserDance> userDances = null;
        try {
            userDanceDao.delete(userDanceToDelete);
            userDances = userDanceDao.getElementsOfTypeAByIdOfEntityOfTypeB("user", userId);
        } catch (Exception exception) {

            logger.debug("Problem deleting user dances: {}", exception);
        }
        return userDances;

    }
}
