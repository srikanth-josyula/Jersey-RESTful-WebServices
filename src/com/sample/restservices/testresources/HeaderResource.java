package com.sample.restservices.testresources;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.sample.restservices.vo.DateInfo;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Path("/header")
public class HeaderResource {

	@GET
	@Path("/test1")
	public String allHeaders(@Context HttpHeaders headers) {
		MultivaluedMap<String, String> rh = headers.getRequestHeaders();
		String str = rh.entrySet().stream().map(e -> e.getKey() + " = " + e.getValue())
				.collect(Collectors.joining("<br/>"));

		return str;
	}

	@GET
	@Path("/test2")
	public String acceptableLanguages(@Context HttpHeaders headers) {
		List<Locale> locales = headers.getAcceptableLanguages();
		return locales.stream().map(l -> l.toString()).collect(Collectors.joining("<br/>"));
	}

	@GET
	@Path("/test3")
	public String requestParamTest1(@HeaderParam(HttpHeaders.HOST) String host) {
		return "The request 'host' header value = " + host;
	}

	@GET
	@Path("/test4")
	public String requestParamTest2(@HeaderParam("anInt") int anInteger) {
		return "The request header 'anInt' value: " + anInteger;
	}

	/**
	 * A type which has a String accepting constructor and is annotated
	 * with @HeaderParam, can be initialized automatically with the target request
	 * header. For example java.util.Date: new Date("Fri, 3 March 2017 20:35:00 GMT");
	 */
	
	@GET
	@Path("/test5")
	public String requestParamTest3(@HeaderParam("aDate") Date date) {
		return "The request  header 'aDate' value:  " + date;
	}

	@GET
	@Path("/test6")
	public String valueOfTest(@HeaderParam("aDate") DateInfo dateInfo) {
		return "The request  header 'aDate' converted to LocalDateTime:  " + dateInfo.asLocalDateTime();
	}

	@GET
	@Path("/test7")
	public String paramConverterTest(@HeaderParam("aDate") LocalDateTime date) {
		return "The request  header 'aDate' converted by MyDateConverter:  " + date;
	}

	@GET
	@Path("/test8")
	public String collectionTest(@HeaderParam("myHeader") List<String> list) {
		String rv = "header collection values: \n";
		rv += list.stream().map(Object::toString).collect(Collectors.joining("\n"));
		return rv;
	}

	@GET
	@Path("/test10")
	public Response responseHeaderTest1() {
		Response.ResponseBuilder rb = Response.ok("the test response");
		Response response = rb.header("header1", "value1").header("header2", "value2").build();
		return response;
	}

	@GET
	@Path("/test11")
	public String clientTest(@Context HttpHeaders headers) {
		MultivaluedMap<String, String> rh = headers.getRequestHeaders();
		String str = rh.entrySet().stream().map(e -> e.getKey() + " = " + e.getValue())
				.collect(Collectors.joining("\n"));

		return str;
	}
}