package com.danceUpByStas.utilities;

import com.danceUpByStas.persistence.SessionFactoryProvider;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    private Logger logger = LogManager.getLogger(this.getClass());
    public ContextListener() {

    }


    public void contextInitialized(ServletContextEvent servletContextEvent) {


    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        SessionFactoryProvider.getSessionFactory().close();
        SessionFactoryProvider.closeSingleton();

        try {
            AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException interruptedException) {

            logger.debug("interrupted exception: {}", interruptedException);
        }

    }

}
