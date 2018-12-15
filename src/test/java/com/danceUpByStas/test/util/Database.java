package com.danceUpByStas.test.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Provides access the database
 * Created on 8/31/16.
 *
 * @author pwaite
 */
public class Database {

    // create an object of the class Database
    private static Database instance = new Database();

    private Properties properties;

    private Connection connection;
    private final Logger logger = LogManager.getLogger(this.getClass());


    // private constructor prevents instantiating this class anywhere else
    private Database() {
        loadProperties();

    }

    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream("/database.properties"));
        } catch (IOException ioe) {
            logger.debug("Database.loadProperties()...Cannot load the properties file");
            ioe.printStackTrace();
        } catch (Exception e) {
            logger.debug("Database.loadProperties()..." + e);
            e.printStackTrace();
        }

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
// get the only Database object available
    public static Database getInstance() {
        return instance;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Connect.
     *
     * @throws Exception the exception
     */
    public void connect() throws Exception {
        if (connection != null)
            return;

        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            throw new Exception("Database.connect()... Error: MySQL Driver not found");
        }

        String url = properties.getProperty("url");
        connection = DriverManager.getConnection(url, properties.getProperty("username"),  properties.getProperty("password"));
    }

    /**
     * Disconnect.
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.debug("Cannot close connection" + e);
            }
        }

        connection = null;
    }


    /**
     * Run sql.
     *
     * @param sqlFile the sql file
     */
    public void runSQL(String sqlFile) {

        Statement statement = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(sqlFile);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            Class.forName("com.mysql.jdbc.Driver");
            connect();
            statement = connection.createStatement();

            while (true) {

                String sql = reader.readLine();
                if (sql == null) {
                    break;
                }
                statement.executeUpdate(sql);

            }

        } catch (SQLException sqlException) {

            logger.error(sqlException);
        } catch (Exception exception) {

            logger.error(exception);
        } finally {
            disconnect();
        }

    }

}