package com.sample.restservices.service;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sample.restservices.vo.Order;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


@Path("v1/orders")
public class OrderService {
	@GET
	public String getOrders(
			@QueryParam("stateCode") String stateCode, 
			@QueryParam("dateFrom") String fromDateString,
			@QueryParam("dateTo") String toDateString, 
			@DefaultValue("-1") @QueryParam("pageStart") int pageStart,
			@DefaultValue("-1") @QueryParam("pagesSize") int pageSize) 
	{
		String message = "Started from all orders. ";
		
		if (stateCode != null) {
			message += "filtered orders for the state " + stateCode + ". ";
		}
		if (fromDateString != null && toDateString != null) {
			message += "Filtered orders for the date range, from " + fromDateString + " to " + toDateString + ". ";
		}
		if (pageStart != -1 && pageSize != -1) {
			message += " Did pagination from order start: " + pageStart + ", pagesSize: " + pageSize;
		}

		return message;
	}

	@GET
	@Path("{orderId:[a-z]\\d{2,3}}")
	public String getOrderById(@PathParam("orderId") String orderId) {
		return "returning order with id " + orderId;
	}

	@GET
	@Path("{orderId}/items")
	public String getOrderItemsPricedGreaterThan(
			@PathParam("orderId") String orderId,
			@QueryParam("minPrice") BigDecimal minPrice) 
	{
		String message = "Starting with all items for order id: " + orderId + ". ";
		if (minPrice != null) {
			message += "Filtered items with price greater than inclusively: " + minPrice.toPlainString();
		}
		return message;
	}

	/**
	 * The Response. Response Builder class provides a convenient way to create a Response instance using a builder pattern.
	 * */
	
	@GET
	@Path("{orderId:\\d+}/items/{itemId:[a-z]\\d{2,3}}")
	public Response getOrderItemByItemId(
			@PathParam("orderId") String orderId, 
			@PathParam("itemId") String itemId) 
	{
		return Response.status(Response.Status.OK)
				.entity("order id: " + orderId + " and item id: " + itemId)
				.build();
	}

	@GET
	@Path("{orderId}/items/{itemId}")
	public Response getInvalidOrderItemByItemId(
			@PathParam("orderId") String orderId,
			@PathParam("itemId") String itemId) 
	{
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("The requested order id or item id are not valid. order id: " + orderId + " and item id: " + itemId)
				.build();
	}
	
	@Path("/orders")
    @Produces(MediaType.APPLICATION_XML)
    @GET
    public Response handle() {
        List<Order> orders = getOrders();
        Response r = Response.ok(orders)
                             .header("someHeader", "someHeaderValue")
                             .build();
        return r;
    }

    public List<Order> getOrders() {
        Order o1 = new Order(1, new BigDecimal(15));
        Order o2 = new Order(2, new BigDecimal(20));
        return Arrays.asList(o1, o2);
    }

    @Path("/orders2")
    @Produces(MediaType.APPLICATION_XML)
    @GET
    public Response handle2() {
        List<Order> orders = getOrders();
        GenericEntity<List<Order>> genericEntity = new GenericEntity<List<Order>>(orders) {
        };//needs empty body to preserve generic type
        Response r = Response.ok(genericEntity)
                             .header("someHeader", "someHeaderValue")
                             .build();
        return r;
    }
}