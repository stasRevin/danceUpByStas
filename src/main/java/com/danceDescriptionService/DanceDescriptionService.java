package com.danceDescriptionService;

import com.danceUpByStas.entity.Dance;
import com.danceUpByStas.persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * This is the DanceDescriptionService class designed to provide the description of the dances.
 * @author srevin
 */
@Path("/danceDescription")
public class DanceDescriptionService {


    /**
     * This method returns dance description based on the dance's name.
     * @param danceName The name of the dance.
     * @return dance The dance description.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
    @Path("/{param}")
    public Dance getDanceDescription(@PathParam("param") String danceName) {

        GenericDao<Dance> danceDao = getGenericDao();

        Dance dance = danceDao.getElementByProperty("name", danceName);

        return dance;

    }

    /**
     * This method returns all dance descriptions.
     * @return response The list of dance descriptions.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
    public Response getDanceDescription() {

        GenericDao<Dance> danceDao = getGenericDao();

        List<Dance> danceList = danceDao.getAll();

        return Response.status(200).entity(danceList).build();

    }

    /**
     * This method returns an instance of a generic Data Access Object.
     * @return genericDao The generic Data Access Object.
     */
    private GenericDao<Dance> getGenericDao() {

        return new GenericDao<Dance>(Dance.class);
    }

}
