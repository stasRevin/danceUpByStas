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

@Path("/inputValidator")
public class InputValidationService extends InputValidator {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private Properties properties = loadProperties("/regexValidation.properties");
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


    @GET
    @Path("/validateName/{name}")
    public Response validateFirstName(@PathParam("name")String name) {

        if (!validateMatchesRegularExpression(name, properties.getProperty("regex.name")))
            return Response.status(200).entity(false).header("Access-Control-Allow-Origin", "*").build();

        return Response.status(200).entity(true).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/validateAddress/{address}")
    public Response validateLastName(@PathParam("address")String address) {

        if (!validateMatchesRegularExpression(address, properties.getProperty("regex.address")))
            return Response.status(200).entity(false).header("Access-Control-Allow-Origin", "*").build();

        return Response.status(200).entity(true).header("Access-Control-Allow-Origin", "*").build();
    }


    @GET
    @Path("/validateZipCode/{zipCode}")
    public Response validateZipCode(@PathParam("zipCode")String zipCode) {

        if (!validateMatchesRegularExpression(zipCode, properties.getProperty("regex.zipcode")))
            return Response.status(200).entity(false).header("Access-Control-Allow-Origin", "*").build();

        return Response.status(200).entity(true).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/validateState/{state}")
    public Response validateState(@PathParam("state")String state) {

        if (!validateMatchesRegularExpression(state, properties.getProperty("regex.state")))
            return Response.status(200).entity(false).header("Access-Control-Allow-Origin", "*").build();

        return Response.status(200).entity(true).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/validateRate/{rate}")
    public Response validateRate(@PathParam("rate")String rate) {

        if (!validateMatchesRegularExpression(rate, properties.getProperty("regex.double")))
            return Response.status(200).entity(false).header("Access-Control-Allow-Origin", "*").build();

        return Response.status(200).entity(true).header("Access-Control-Allow-Origin", "*").build();
    }


}
