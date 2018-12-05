package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Role;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserRole;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.SignUpInputValidator;
import com.danceUpByStas.utilities.UserPhotoManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.apache.catalina.realm.RealmBase;

@WebServlet(
        name = "UserSignUp",
        urlPatterns = "/userSignUp")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 100,
        maxRequestSize = 1024 * 1024 * 500
)

public class UserSignUp extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        SignUpInputValidator inputValidator = new SignUpInputValidator();

        if (!inputValidator.runInputValidator(request.getParameterMap())) {

            response.sendRedirect("/danceup/generalError.jsp");
            return;
        }

        String role = request.getParameter("role");
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipCode = request.getParameter("zip");
        String password = request.getParameter("password");
        String rateInput = request.getParameter("ratePerLesson");
        Double payrate = 0.0;

        String hashedPassword = RealmBase.Digest(password, "SHA-256", "UTF-8");


        if (role.equals("instructor") && !Objects.isNull(rateInput) && !rateInput.equals("")) {

            payrate = Double.parseDouble(rateInput);
        }

        User user = new User(username, hashedPassword, 0, firstName, lastName, address, city, state,
                             zipCode, payrate, "");
        persistUser(user, request, context);
        associateUserWithRole(role, user);

        response.sendRedirect("/danceup");
    }


    private int persistUser(User user, HttpServletRequest request, ServletContext context) throws ServletException, IOException {

        GenericDao<User> userDao = new GenericDao<User>(User.class);
        UserPhotoManager photoManager = new UserPhotoManager();
        int userId = userDao.insert(user);

        File userPhotoFile = new File((String)context.getAttribute("profilePhotoPath"));
        File userFolder = new File(userPhotoFile + File.separator + userId);

        photoManager.saveUserPhoto(request, userFolder, user, userDao);

        return userId;

    }


    private void associateUserWithRole(String role, User user) {

        GenericDao<UserRole> userRoleDao = new GenericDao<>(UserRole.class);
        GenericDao<Role> roleDao = new GenericDao<Role>(Role.class);

        if (role.equals("instructor")) {
            Role instructor = roleDao.getById(1);
            userRoleDao.insertManyToMany(new UserRole(user, instructor));

        } else if (role.equals("student")) {

            Role student = roleDao.getById(2);
            userRoleDao.insertManyToMany(new UserRole(user, student));
        }

    }


}
