package com.sample.restservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 
@Path("/show")
public class JerseyHelloWorldService
{
    @GET
    @Path("/{message}")
    public Response getMsg(@PathParam("message") String msg)
    {
        String output = "Message requested : " + msg;
        return Response.status(200).entity(output).build();
    }
}