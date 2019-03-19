package com.sample.restservices.testresources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/propertyinject")
public class PropertyInjectionResource {
    UriInfo uriInfo;
    int pathNum;
    String myParam;

    @GET
    @Path("/test{num:[1-9]{0,1}}")
    public String handleRequest() {
        URI uri = uriInfo.getRequestUri();
        return String.format("response from: %s%nPathNum: %s%nmyParam: %s%n",
                uri, pathNum, myParam);
    }

    @Context
    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @PathParam("num")
    public void setPathNum(int pathNum) {
        this.pathNum = pathNum;
    }

    @QueryParam("q")
    public void setMyParam(String myParam) {
        this.myParam = myParam;
    }
}