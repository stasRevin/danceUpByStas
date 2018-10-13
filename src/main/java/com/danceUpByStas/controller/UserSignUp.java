package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Role;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserRole;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.UserPhotoManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

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
        File userPhotoFile = new File((String)context.getAttribute("profilePhotoPath"));

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
        String passwordConfirmation = request.getParameter("passwordConfirmation");
        Double payrate = 0.0;

        String hashedPassword = "";

        if (password.equals(passwordConfirmation)) {

            hashedPassword = RealmBase.Digest(password, "SHA-256", "UTF-8");

        } else {

            response.sendRedirect("/danceup/generalError.jsp");
        }


        //TODO: add pay rate for instructor

        //Create new User
        if (role.equals("instructor")) {

            payrate = Double.parseDouble(request.getParameter("ratePerLesson"));
        }

        User user = new User(username, hashedPassword, 0, firstName, lastName, address1, address2, city, state, zipCode, payrate, "");
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

        File userFolder = new File(userPhotoFile + File.separator + userId);


        if (!userFolder.exists()) {

            userFolder.mkdir();
        }

        UserPhotoManager photoManager = new UserPhotoManager();
        photoManager.saveUserPhoto(request, userFolder, user, userDao);

        response.sendRedirect("/danceup/signIn.jsp");
    }


    //http://tutorials.jenkov.com/java-cryptography/messagedigest.html
    //https://www.mkyong.com/java/how-do-convert-byte-array-to-string-in-java/
    private String hashPassword(String password, byte[] salt, int iterationCount, int keyLength, String hashingAlgorithm) {

        Logger logger = LogManager.getLogger(this.getClass());
        String hashedPassword = "";
        char[] charPassword = password.toCharArray();

        PBEKeySpec spec = new PBEKeySpec(charPassword, salt, iterationCount, keyLength);
        Arrays.fill(charPassword, Character.MIN_VALUE);

        try {

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(hashingAlgorithm);
            byte[] byteHash = secretKeyFactory.generateSecret(spec).getEncoded();
            hashedPassword = new String(byteHash, "Unicode");

        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {

            logger.debug("No such algorithm exception: {}", noSuchAlgorithmException);
        } catch (InvalidKeySpecException invalidKeyException) {

            logger.debug("Invalid Key Exception: {}", invalidKeyException);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {

            logger.debug("Unsupported encoding: {}", unsupportedEncodingException);

        } finally {

            spec.clearPassword();
        }


        return hashedPassword;

    }

    private byte[] getSalt(int length) {

        Random random = new SecureRandom();
        byte[] salt = new byte[length];
        random.nextBytes(salt);

        return salt;
    }

}
