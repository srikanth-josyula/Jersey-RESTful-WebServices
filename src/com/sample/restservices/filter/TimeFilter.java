package com.sample.restservices.filter;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Priority(1)
@Provider
public class TimeFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext reqContext) throws IOException {
        System.out.println("-- TimeFilter request --");
        reqContext.setProperty("start-time", System.currentTimeMillis());
    }

    @Override
    public void filter(ContainerRequestContext reqContext,
                       ContainerResponseContext resContext) throws IOException {

        System.out.println("-- TimeFilter response --");

        long startTime = (long) reqContext.getProperty("start-time");
        System.out.printf("Time taken for request %s:  %s milli secs%n",
                reqContext.getUriInfo().getPath(),
                System.currentTimeMillis() - startTime
        );
    }
}
