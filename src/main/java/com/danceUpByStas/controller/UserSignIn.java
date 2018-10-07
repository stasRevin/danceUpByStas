package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Dance;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserDance;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.SimpleVisitor;

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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(
        name = "com.danceUpByStas.controller.UserSignIn",
        urlPatterns = {"/signInUser"}

)
public class UserSignIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        String url = "";
        GenericDao<User> userDao = new GenericDao<>(User.class);

        Map<String, String> userLoginProperties = new HashMap<>();
        userLoginProperties.put("username", username);
        userLoginProperties.put("password", password);
        List<User> userList = userDao.getElementsByMultipleProperties(userLoginProperties);
        User user = userList.get(0);

        if (Objects.nonNull(user)) {

            int userId = user.getId();
            String photoName = user.getPhotoName();

            List<UserDance> dances = user.getDances();
            GenericDao<UserDance> userDanceDao = new GenericDao<>(UserDance.class);
            List<UserDance> userDances = userDanceDao.getElementsOfTypeAByIdOfEntityOfTypeB("user", userId);

            session.setAttribute("user", user);
            session.setAttribute("userDances", userDances);
            String userPhotoPath = (String)context.getAttribute("profilePhotoPath")
                                + File.separator + userId + File.separator + user.getPhotoName();

            prepareUserPhoto(userPhotoPath, photoName);
            url = role.equals("instructor") ? "/instructorViewProfile.jsp" : "/studentViewProfile.jsp";

        } else {

            url = "/signIn.jsp";
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }

        RequestDispatcher dispatcher = context.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    private void prepareUserPhoto(String userPhotoPath, String photoName) {

        //String staticImagePath = "/Users/stanislavrevin/Desktop/MATC/EnterpriseJava/indieProject/target/danceup/images/userPhotos";
        String catalinaHome = System.getProperty("catalina.home");
        String staticImagePath = catalinaHome +  File.separator + "webapps/danceup/images/userPhotos";
        File imageDirectory = new File(staticImagePath);

        Path source = Paths.get(userPhotoPath);
        Path target = Paths.get(staticImagePath + File.separator + photoName);
        Logger logger = LogManager.getLogger(this.getClass());

        SimpleVisitor simpleVisitor = new SimpleVisitor(target, source);

        try {

            Files.walkFileTree(source, simpleVisitor);

        } catch (IOException inputOutputException) {

            logger.debug("Input/Output Exception: {}",  inputOutputException);

        }

    }


}
