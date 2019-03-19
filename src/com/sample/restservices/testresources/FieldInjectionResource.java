package com.sample.restservices.testresources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/fieldinject")
public class FieldInjectionResource {
	@Context
	UriInfo uriInfo;
	@PathParam("num")
	int pathNum;
	@QueryParam("q")
	String qValue;

	@GET
	@Path("/test{num:[1-9]{0,1}}")
	public String handleRequest() {
		URI uri = uriInfo.getRequestUri();
		return String.format(" response from: %s%n PathNum: %s%n query paramMyResource: %s%n", uri, pathNum, qValue);
	}
}