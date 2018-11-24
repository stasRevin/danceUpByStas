package com.danceUpByStas.controller;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.InputValidator;
import com.danceUpByStas.utilities.ProfileUpdateInputValidator;
import com.danceUpByStas.utilities.UserPhotoManager;
import org.apache.catalina.realm.RealmBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.ws.rs.PathParam;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@WebServlet(name = "UserProfileUpdate",
            urlPatterns = {"/updateUserProfile"})

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 100,
        maxRequestSize = 1024 * 1024 * 500
)
public class UserProfileUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ProfileUpdateInputValidator inputValidator = new ProfileUpdateInputValidator();

        if (!inputValidator.runInputValidator(request.getParameterMap())) {

            response.sendRedirect("/danceup/generalError.jsp");
            return;
        }

        User user = (User)session.getAttribute("user");
        int userId = user.getId();
        String userPhotoFile = (String)getServletContext().getAttribute("profilePhotoPath");
        Integer role = (Integer) session.getAttribute("role");

        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipCode = request.getParameter("zip");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");
        String hashedPassword = "";
        Double instructorRatePerLesson = 0.0;

        GenericDao<User> userDao = new GenericDao<>(User.class);

        if (!password.isEmpty() && password.equals(passwordConfirmation)) {

            hashedPassword = RealmBase.Digest(password, "SHA-256", "UTF-8");
            user.setPassword(hashedPassword);

        }

        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddressOne(address);
        user.setCity(city);
        user.setState(state);
        user.setPostalCode(zipCode);

        if (role == 1 && !Objects.isNull(instructorRatePerLesson) && !instructorRatePerLesson.equals("")) {

            instructorRatePerLesson = Double.parseDouble(request.getParameter("ratePerLesson"));
            user.setPayRate(instructorRatePerLesson);
        }

        updateUserPhoto(userPhotoFile, userId, request, user, userDao);
        userDao.saveOrUpdate(user);
        String url = setUrl(role);
        response.sendRedirect(url);
    }

    private void updateUserPhoto(String userPhotoFile, int userId, HttpServletRequest request, User user, GenericDao<User> userDao)
            throws IOException, ServletException {

        File userFolder = new File(userPhotoFile + File.separator + userId);

        if (request.getPart("profilePhoto").getSize() > 0) {
            UserPhotoManager photoManager = new UserPhotoManager();
            Path photoPath = Paths.get(userPhotoFile + File.separator + userId + File.separator + user.getPhotoName());
            photoManager.deleteUserPhoto(user, photoPath);
            photoManager.saveUserPhoto(request, userFolder, user, userDao);

        }

    }

    private String setUrl(int role) {

        if (role == 1) {

            return "/danceup/signInInstructor";

        }
        else if (role ==2) {

            return "/danceup/studentSignIn";
        }

        return "";
    }

}
