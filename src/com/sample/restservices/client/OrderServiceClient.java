package com.sample.restservices.client;

import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;


public class OrderServiceClient {
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		requestAllOrders(client);
		sendingRequest(client);
		requestOrderById(client, "343");
		requestAllItemsByOrderId(client, "343");
		requestItemByOrderIdAndItemId(client, "343", "343");

		client.close();
	}

	private static void requestItemByOrderIdAndItemId(Client client, String orderId, String itemId) {
		WebTarget target = client
				.target("http://localhost:9090/jaxrs-client-api-example/api/orders/" + orderId + "/items/" + itemId);
		// using response object to get more information
		Response s = target.request().get();
		System.out.println("response: " + s.getEntity() + ",  status " + s.getStatus());
		s.close();
	}

	private static void requestAllItemsByOrderId(Client client, String orderId) {
		WebTarget target = client
				.target("http://localhost:9090/jaxrs-client-api-example/api/orders/" + orderId + "/items");
		String s = target.request().get(String.class);
		System.out.println("response : " + s);
	}

	private static void requestOrderById(Client client, String orderId) {
		WebTarget target = client.target("http://localhost:9090/jaxrs-client-api-example/api/orders/" + orderId);
		String s = target.request().get(String.class);
		System.out.println("response : " + s);

	}

	private static void requestAllOrders(Client client) {
		WebTarget target = client.target("http://localhost:9090/jaxrs-client-api-example/api/orders");
		String s = target.request().get(String.class);
		System.out.println("response : " + s);
	}
	
	public static void sendingRequest(Client client) {
	      WebTarget target = client.target("http://localhost:8080/test11");
	      Invocation.Builder builder = target.request();
	      String response = builder.header("myHeader", "a")
	                               .header("aDateHeader", new Date())
	                               .get(String.class);
	      System.out.println(response);
	  }
}
