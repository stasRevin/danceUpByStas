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

@Path("/danceDescription")
public class DanceDescriptionService {


    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
    @Path("/{param}")
    public Response getDanceDescription(@PathParam("param") String danceName) {

        GenericDao<Dance> danceDao = getGenericDao();

        Dance dance = danceDao.getElementByProperty("name", danceName);

        return Response.status(200).entity(dance).build();

    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
    public Response getDanceDescription() {

        GenericDao<Dance> danceDao = getGenericDao();

        List<Dance> danceList = danceDao.getAll();

        return Response.status(200).entity(danceList).build();

    }


    private GenericDao<Dance> getGenericDao() {

        return new GenericDao<Dance>(Dance.class);
    }

}
