package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Role;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserRole;
import com.danceUpByStas.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

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

        //Get session
        HttpSession session = request.getSession();

        File userPhotoFile = new File("/Users/stanislavrevin/tomcat/webapps/user_photos");

        //Get parameters from the form
        String role = request.getParameter("role");
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipCode = request.getParameter("zip");
        String password = request.getParameter("password");

        //Create new User
        User user = new User(username, password, 0, firstName, lastName, address1, address2, city, state, zipCode, 0.00, "");
        //Insert new User
        GenericDao<User> userDao = new GenericDao<User>(User.class);
        int userId = userDao.insert(user);
        //Associate user with the role
        GenericDao<UserRole> userRoleDao = new GenericDao<>(UserRole.class);
        GenericDao<Role> roleDao = new GenericDao<Role>(Role.class);

        if (role.equals("instructor")) {
            Role instructor = roleDao.getById(1);
            userRoleDao.insertManyToMany(new UserRole(user, instructor));

        } else if (role.equals("student")) {

            Role student = roleDao.getById(2);
            userRoleDao.insertManyToMany(new UserRole(user, student));
        }
        //Forward to the login page

        if (!userPhotoFile.exists()) {

            userPhotoFile.mkdir();
        }

        for (Part part : request.getParts()) {

            if (part.getName().equals("profilePhoto")) {

                String fileName = getFileName(part);
                System.out.println("PART FILE NAME: " + fileName);
                String fileLocation = userPhotoFile + File.separator + fileName;
                part.write(fileLocation);
                user.setPhotoName(fileName);
                userDao.saveOrUpdate(user);
            }
        }

        response.sendRedirect("/signIn.jsp");
    }

    private String getFileName(Part part) {

        String contentDisplay = part.getHeader("content-disposition");
        String fileName = "";
        String[] contentItems = contentDisplay.split(";");

        for (String contentItem : contentItems) {

            if (contentItem.trim().startsWith("filename")) {

                fileName = contentItem.substring(contentItem.indexOf("=") + 2,
                        contentItem.length() - 1);
                return fileName;
            }

        }

        return fileName;
    }

}
