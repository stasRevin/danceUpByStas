package com.danceUpByStas.controller;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.enums.UserRoleEnum;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.ProfileUpdateInputValidator;
import com.danceUpByStas.utilities.UserPhotoManager;
import org.apache.catalina.realm.RealmBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * This is the UserProfileUpdate servlet class designed to facilitate the update of the user profiles.
 * @author srevin
 */
@WebServlet(name = "UserProfileUpdate",
            urlPatterns = {"/updateUserProfile"})

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 100,
        maxRequestSize = 1024 * 1024 * 500
)
public class UserProfileUpdate extends HttpServlet {

    /**
     * This method handles the POST requests.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException The servlet exception.
     * @throws IOException The input output exception.
     */
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
        String rateInput = request.getParameter("ratePerLesson");
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

        if (role == UserRoleEnum.INSTRUCTOR.getRoleNumber() && !Objects.isNull(rateInput) && !rateInput.equals("")) {

            instructorRatePerLesson = Double.parseDouble(rateInput);
            user.setPayRate(instructorRatePerLesson);
        }

        updateUserPhoto(userPhotoFile, userId, request, user, userDao);
        userDao.saveOrUpdate(user);
        String url = setUrl(role);
        response.sendRedirect(url);
    }

    /**
     * This method deletes old user photo and inserts a new user photos.
     * @param userPhotoFile The file where to user photo file is located.
     * @param userId The user id.
     * @param request The HTTP request.
     * @param user The object reference of type user.
     * @param userDao The Data Access Object reference to the objects that handle user related database operations.
     * @throws IOException The input/output exception.
     * @throws ServletException The servlet exception.
     */
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

    /**
     * This method will determine what path to use in order to forward user based on his/her role.
     * @param role The user role.
     * @return path The path to the page where user will be forwarded.
     */
    private String setUrl(int role) {

        if (role == UserRoleEnum.INSTRUCTOR.getRoleNumber()) {

            return "/danceup/signInInstructor";

        }
        else if (role == UserRoleEnum.STUDENT.getRoleNumber()) {

            return "/danceup/studentSignIn";
        }

        return "";
    }

}
