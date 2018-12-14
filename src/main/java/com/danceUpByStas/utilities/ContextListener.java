package com.danceUpByStas.utilities;

import com.danceUpByStas.persistence.SessionFactoryProvider;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * This is the ContextListener class designed to listen to the contextwide events.
 * @author srevin
 */

@WebListener
public class ContextListener implements ServletContextListener {

    private Logger logger = LogManager.getLogger(this.getClass());

    /**
     * The constructor.
     */
    public ContextListener() {

    }

    /**
     * This method performs tasks in response to the context initialization.
     * @param servletContextEvent The servlet context event.
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {


    }

    /**
     * This method performs tasks in response to the context destruction.
     * @param servletContextEvent The servlet context event.
     */
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
