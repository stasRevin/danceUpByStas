package com.danceUpByStas.controller;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.UserDao;
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
        UserDao userDao = new UserDao();

        User user = userDao.sigIn(username, password);

        if (Objects.nonNull(user)) {

            int userId = user.getId();
            String photoName = user.getPhotoName();
            session.setAttribute("user", user);
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

        String staticImagePath = "/Users/stanislavrevin/Desktop/MATC/EnterpriseJava/indieProject/src/main/webapp/images";
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
