package com.sample.restservices.service;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.PathSegment;
import java.util.List;

@Path("/")
public class CustomerService {
	//    /customers;state=TX
    @GET
    @Path("customers")
    public String getCustomers(@MatrixParam("state") String state) {
        String info = String.format("resource getCustomers(). state:%s%n", state);
        return info;
    }

    /**
     * @MatrixParam are not supported for the matrix parameters which are not at the end of the URI. 
     * That is probably because matrix params should closely be tied to the part they belong to.
     * 
     * JAX-RS does not support @MatrixParam to capture the params if they are in the middle, 
     * instead we are provided another way to access matrix parameters i.e. PathSegment.
     * */
    
    //	/customers;state=tx/orders;minBill=60/2017-2
    @GET
    @Path("{var:customers}/orders")
    public String getOrders(@PathParam("var") PathSegment ps) {
        String info = String.format("resource getOrders(). Path:%s, MatrixParams:%s%n",
                ps.getPath(), ps.getMatrixParameters());
        return info;
    }

    //	/customers;state=tx/orders;minBill=60/2017-2
    @GET
    @Path("{var1:customers}/{var2:orders}/{month}")
    public String getMonthOrders(@PathParam("var1") PathSegment customerPs,
                                 @PathParam("var2") PathSegment orderPs,
                                 @PathParam("month") String month) {
        String info = String.format("resource getMonthOrders(). Customers Matrix Param: %s," +
                        " Orders Matrix Param: %s, month :%s%n", customerPs.getMatrixParameters(),
                orderPs.getMatrixParameters(), month);
        return info;
    }

    //	/testA/test1;param1=val1;param2=val2/test2/test3;param3=val3
    @GET
    @Path("testA/{var:.+}")
    public String testList(@PathParam("var") List<PathSegment> pathSegmentList) {
        String temp = "";
        for (PathSegment pathSegment : pathSegmentList) {
         temp+= String.format("Path: %s, Matrix Params %s<br/>", pathSegment.getPath(),
                 pathSegment.getMatrixParameters());
        }

        String info = String.format("resource testList.<br/> Matrix Param List:<br/> %s<br/>", temp);
        return info;
    }
}