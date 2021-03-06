package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Notification;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserDance;
import com.danceUpByStas.entity.UserLesson;
import com.danceUpByStas.enums.UserRoleEnum;
import com.danceUpByStas.persistence.GenericDao;
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
import java.util.List;
import java.util.Objects;

/**
 *This is the StudentSignIn servlet class designed to facilitate the signing in process of the student.
 * @author srevin
 */
@WebServlet(name = "StudentSignIn",
            urlPatterns = "/studentSignIn")
public class StudentSignIn extends HttpServlet {

    /**
     * This method handles the POST requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        final Logger logger = LogManager.getLogger(this.getClass());
        HttpSession session = request.getSession();

        String username = request.getUserPrincipal().getName();
        String url = "";
        GenericDao<User> userDao = new GenericDao<>(User.class);

        User user = userDao.getElementByProperty("username", username);
        logger.debug("User signing in before null check: {}", user);

        if (Objects.nonNull(user)) {

            int userId = user.getId();
            String photoName = user.getPhotoName();

            UserSignInHelper signInHelper = new UserSignInHelper();

            List<UserDance> userDances = signInHelper.getUserDances(userId);
            List<UserLesson> userLessons = signInHelper.getUserLessons(userId, UserRoleEnum.STUDENT.getRoleNumber());
            userLessons = signInHelper.getUsersForLessons(userLessons, UserRoleEnum.INSTRUCTOR.getRoleNumber());
            List<Notification> notifications = signInHelper.getNotifications(user);

            session.setAttribute("user", user);
            session.setAttribute("role", UserRoleEnum.STUDENT.getRoleNumber());
            session.setAttribute("userDances", userDances);
            session.setAttribute("userLessons", userLessons);
            logger.debug("Signing in user: {}", user);

            String userPhotoPath = (String)context.getAttribute("profilePhotoPath")
                    + File.separator + userId + File.separator + user.getPhotoName();
            String usersFoundPhotosPath = (String)context.getAttribute("usersFoundPhotosPath") + userId;
            String staticUserPhotoPath = (String)context.getAttribute("staticUserPhotoPath") + userId;

            session.setAttribute("userPhotoPath", userPhotoPath);

            UserPhotoManager photoManager = new UserPhotoManager();
            String photoDirectoryName = (String)context.getAttribute("photoDirectoryName") + userId;
            session.setAttribute("usersFoundPhotosPath", usersFoundPhotosPath);
            session.setAttribute("userPhotoDirectory", staticUserPhotoPath);
            session.setAttribute("notifications", notifications);

            photoManager.prepareUserPhoto(userPhotoPath, photoDirectoryName, photoName);
            url = "/studentViewProfile.jsp";

        } else {

            url = "/generalError.jsp";
        }

        RequestDispatcher dispatcher = context.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * The doGet method designed to accept GET requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);

    }

}
