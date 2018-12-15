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
import javax.ws.rs.core.Response;

/**
 * This is the InputValidationService designed to provide methods to validate the user input.
 * @author srevin
 */
@Path("/inputValidator")
public class InputValidationService extends InputValidator {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private Properties properties = loadProperties("/regexValidation.properties");

    /**
     * This method validates the username.
     * @param username The given username.
     * @return response The service response.
     */
    @GET
    @Path("/validateUsername/{username}")
    public Response validateUsername(@PathParam("username")String username) {

        GenericDao<User> userDao = new GenericDao<User>(User.class);
        User user = null;

        try {
            user = userDao.getElementByProperty("username", username);

        } catch (javax.persistence.NoResultException noResultException) {

            logger.debug("User with username {} was not found.", username);
        }

        if (!Objects.isNull(user))
            return Response.status(200).entity(false).header("Access-Control-Allow-Origin", "*").build();

        return Response.status(200).entity(true).header("Access-Control-Allow-Origin", "*").build();
    }

    /**
     * This method validates first or last name.
     * @param name The given first or last name.
     * @return response The service response.
     */
    @GET
    @Path("/validateName/{name}")
    public Response validateName(@PathParam("name")String name) {

        if (!validateMatchesRegularExpression(name, properties.getProperty("regex.name")))
            return Response.status(200).entity(false).header("Access-Control-Allow-Origin", "*").build();

        return Response.status(200).entity(true).header("Access-Control-Allow-Origin", "*").build();
    }

    /**
     * This method validates address input.
     * @param address The address input.
     * @return response The service response.
     */
    @GET
    @Path("/validateAddress/{address}")
    public Response validateAddress(@PathParam("address")String address) {

        if (!validateMatchesRegularExpression(address, properties.getProperty("regex.address")))
            return Response.status(200).entity(false).header("Access-Control-Allow-Origin", "*").build();

        return Response.status(200).entity(true).header("Access-Control-Allow-Origin", "*").build();
    }

    /**
     * This method validates zip code.
     * @param zipCode The given zip code.
     * @return response The service response.
     */
    @GET
    @Path("/validateZipCode/{zipCode}")
    public Response validateZipCode(@PathParam("zipCode")String zipCode) {

        if (!validateMatchesRegularExpression(zipCode, properties.getProperty("regex.zipcode")))
            return Response.status(200).entity(false).header("Access-Control-Allow-Origin", "*").build();

        return Response.status(200).entity(true).header("Access-Control-Allow-Origin", "*").build();
    }

    /**
     * This method validates state.
     * @param state The given state.
     * @return response. The service response.
     */
    @GET
    @Path("/validateState/{state}")
    public Response validateState(@PathParam("state")String state) {

        if (!validateMatchesRegularExpression(state, properties.getProperty("regex.state")))
            return Response.status(200).entity(false).header("Access-Control-Allow-Origin", "*").build();

        return Response.status(200).entity(true).header("Access-Control-Allow-Origin", "*").build();
    }

    /**
     * This method validates the pay rate.
     * @param rate The given pay rate.
     * @return response. The service response.
     */
    @GET
    @Path("/validateRate/{rate}")
    public Response validateRate(@PathParam("rate")String rate) {

        if (!validateMatchesRegularExpression(rate, properties.getProperty("regex.double")))
            return Response.status(200).entity(false).header("Access-Control-Allow-Origin", "*").build();

        return Response.status(200).entity(true).header("Access-Control-Allow-Origin", "*").build();
    }

}
