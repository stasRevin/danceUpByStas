package com.danceUpByStas.controller;

import com.danceUpByStas.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@WebServlet(
        name = "ApplicationStartup",
        urlPatterns = {"/applicationStartup"},
        loadOnStartup = 1
)
public class ApplicationStartup extends HttpServlet implements PropertiesLoader {

    public void init() throws ServletException {

        Logger logger = LogManager.getLogger(this.getClass());

        ServletContext context = getServletContext();
        Properties properties = loadProperties("/photoManagerLocal.properties");
        String staticImagesDirectory = properties.getProperty("staticImagePath");
        String catalinaHome = System.getProperty("catalina.home");

        String photoDirectoryName = "userPhotos";
        String usersFoundPhotosDirectory = "usersFoundPhotos";

        context.setAttribute("profilePhotoPath",catalinaHome + File.separator + "user_photos");
        context.setAttribute("photoDirectoryName", photoDirectoryName);
        context.setAttribute("usersFoundPhotosDirectory", usersFoundPhotosDirectory);

        String usersFoundPhotosPath = staticImagesDirectory + File.separator + usersFoundPhotosDirectory;
        String staticUserPhotoPath = staticImagesDirectory + File.separator + photoDirectoryName;

        context.setAttribute("usersFoundPhotosPath", usersFoundPhotosPath);
        context.setAttribute("staticUserPhotoPath", staticUserPhotoPath);

    }

}
