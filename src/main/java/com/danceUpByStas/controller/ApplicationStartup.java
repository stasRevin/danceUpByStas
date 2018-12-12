package com.danceUpByStas.controller;

import com.danceUpByStas.utilities.PropertiesLoader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.util.Properties;

@WebServlet(
        name = "ApplicationStartup",
        urlPatterns = {"/applicationStartup"},
        loadOnStartup = 1
)
/**
 * This the is ApplicationStartup class designed to set the context environment of the application.
 * @author srevin
 */
public class ApplicationStartup extends HttpServlet implements PropertiesLoader {

    /**
     * This method initializes the application.
     * @throws ServletException The servlet exception.
     */
    public void init() throws ServletException {

        ServletContext context = getServletContext();
        Properties properties = loadProperties("/photoManagerRemote.properties");
        String staticImagesDirectory = properties.getProperty("staticImagePath");
        String catalinaHome = System.getProperty("catalina.home");
        String defaultRadius = "5";

        String photoDirectoryName = "userPhotos";
        String usersFoundPhotosDirectory = "usersFoundPhotos";

        context.setAttribute("profilePhotoPath",catalinaHome + File.separator + "user_photos");
        context.setAttribute("photoDirectoryName", photoDirectoryName);
        context.setAttribute("usersFoundPhotosDirectory", usersFoundPhotosDirectory);

        String usersFoundPhotosPath = staticImagesDirectory + File.separator + usersFoundPhotosDirectory;
        String staticUserPhotoPath = staticImagesDirectory + File.separator + photoDirectoryName;

        context.setAttribute("usersFoundPhotosPath", usersFoundPhotosPath);
        context.setAttribute("staticUserPhotoPath", staticUserPhotoPath);
        context.setAttribute("staticImagePath", staticImagesDirectory);
        context.setAttribute("defaultRadius", defaultRadius);

    }

}
