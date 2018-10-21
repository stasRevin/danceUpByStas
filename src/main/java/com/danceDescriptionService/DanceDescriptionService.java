package com.danceDescriptionService;

import com.danceUpByStas.entity.Dance;
import com.danceUpByStas.persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/danceDescription")
public class DanceDescriptionService {


    @GET
    @Produces("application/json")
    @Path("/{param}")
    public Response getDanceDescription(@PathParam("param") String danceName) {

        GenericDao<Dance> danceDao = new GenericDao<>(Dance.class);

        Dance dance = danceDao.getElementByProperty("name", danceName);

        return Response.status(200).entity(dance).build();

    }

}
