package com.danceUpByStas.controller;

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
public class ApplicationStartup extends HttpServlet {

    public void init() throws ServletException {

        ServletContext context = getServletContext();
        String catalinaHome = System.getProperty("catalina.home");
        context.setAttribute("profilePhotoPath",catalinaHome + File.separator + "user_photos");
        Properties properties = System.getProperties();
    }

}
