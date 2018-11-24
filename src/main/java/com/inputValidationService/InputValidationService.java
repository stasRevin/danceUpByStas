package com.inputValidationService;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import com.danceUpByStas.utilities.InputValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Objects;
import java.util.Properties;

@Path("/inputValidator")
public class InputValidationService extends InputValidator {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private Properties properties = loadProperties("/regexValidation.properties");
    @GET
    @Path("/validateUsername/{username}")
    public boolean validateUsername(@PathParam("username")String username) {

        GenericDao<User> userDao = new GenericDao<User>(User.class);
        User user = null;

        try {
            user = userDao.getElementByProperty("username", username);

        } catch (javax.persistence.NoResultException noResultException) {

            logger.debug("User with username {} was not found.", username);
        }

        if (Objects.isNull(user))
            return true;

        return false;
    }


    @GET
    @Path("/validateFirstName/{firstName}")
    public boolean validateFirstName(@PathParam("firstName")String firstName) {

        if (!validateMatchesRegularExpression(firstName, properties.getProperty("regex.alpha")))
            return false;

        return true;
    }

    @GET
    @Path("/validateLastName/{lastName}")
    public boolean validateLastName(@PathParam("lastName")String lastName) {

        if (!validateMatchesRegularExpression(lastName, properties.getProperty("regex.alpha")))
            return false;

        return true;
    }


}
