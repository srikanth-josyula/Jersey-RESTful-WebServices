package com.sample.restservices.testresources;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/cookies")
public class CookieResource {

    @GET
    @Path("/test1")
    public Response createCookies() {
        NewCookie cookie1 = new NewCookie("myStrCookie", "cookieStrVal");
        NewCookie cookie2 = new NewCookie("myDateCookie", "2017-03-28");
        NewCookie cookie3 = new NewCookie("myIntCookie", "100");
        Response.ResponseBuilder rb = Response.ok("myStrCookie, "
                + "myDateCookie and myIntCookie sent to the browser");
        Response response = rb.cookie(cookie1, cookie2, cookie3)
                              .build();
        return response;
    }
    
    @GET
    @Path("/test2")
    public String readAllCookies(@Context HttpHeaders headers) {
        Map<String, Cookie> cookies = headers.getCookies();
        String str = cookies.entrySet()
                            .stream()
                            .map(e -> e.getKey() + " = " + e.getValue().getValue())
                            .collect(Collectors.joining("<br/>"));
        return str;
    }
    
    @GET
    @Path("/test3")
    public String readCookie1(@CookieParam("myStrCookie") String strCookie) {
        return "myStrCookie value = " + strCookie;
    }

    @GET
    @Path("/test4")
    public String readCookie2(@CookieParam("myIntCookie") int intCookie) {
        return "myIntCookie value  = " + intCookie;
    }

    @GET
    @Path("/test5")
    public String readCookie3(@CookieParam("myIntCookie") BigDecimal bd) {
        return "myIntCookie value in BigDecimal = " + bd;
    }

    @GET
    @Path("/test6")
    public String readCookie4(@CookieParam("myIntCookie") Long aLong) {
        return "myIntCookie  in Long :" + aLong;
    }

    @GET
    @Path("/test7")
    public String readCookie5(@CookieParam("myDateCookie") Cookie cookie) {
        return "Cookie object :" + cookie;
    }

    @GET
    @Path("/test8")
    public String readCookie6(@CookieParam("myDateCookie") LocalDate date) {
        return "myDateCookie as LocalDate :" + date;
    }

    @GET
    @Path("/test9")
    public Response writeCookies() {
        NewCookie cookie1 = new NewCookie("myCookie", "cookieStrVal");
        NewCookie cookie2 = new NewCookie("myCookie", "cookieStrVal2");
        Response.ResponseBuilder rb = Response.ok(" Multiple values of myCookie"
                + " sent to the browser");
        Response response = rb.cookie(cookie1, cookie2)
                              .build();
        return response;
    }

    @GET
    @Path("/test10")
    public String readCookie7(@CookieParam("myCookie") List<String> list) {
        String rv = "List size: " + list.size() +
                "<br/>List values:<br/> ";
        rv += list.stream()
                  .collect(Collectors.joining("<br/>"));
        return rv;
    }

}