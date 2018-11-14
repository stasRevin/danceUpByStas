package com.danceUpByStas.utilities;

import com.danceUpByStas.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.File;
@WebListener
public class SessionListener implements HttpSessionListener {

    private Logger logger = LogManager.getLogger(this.getClass());

    public void sessionCreated(HttpSessionEvent sessionEvent) {

        //number of seconds
        sessionEvent.getSession().setMaxInactiveInterval(10 * 60);

        logger.debug("Session with id of {} was created.", sessionEvent.getSession().getId());

    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {

        HttpSession _session = sessionEvent.getSession();

        String usersFoundPhotosFile = (String) _session.getAttribute("usersFoundPhotosPath");
        String userPhotoDirectory = (String) _session.getAttribute("userPhotoDirectory");

        long _start = _session.getCreationTime();
        long _end = _session.getLastAccessedTime();

        logger.debug("Session with id of {} was destroyed. Session duration time: {} minutes"
                    , _session.getId(), (((_end - _start) / 1000) / 60));

        UserPhotoManager manager = new UserPhotoManager();

        logger.debug("userFoundPhotos photo folder to remove: {}",usersFoundPhotosFile);
        logger.debug("userPhotos to remove: {}", userPhotoDirectory);

        manager.deleteUserPhotos(new File(usersFoundPhotosFile));
        manager.deleteUserPhotos(new File(userPhotoDirectory));

    }

}
