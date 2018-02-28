package com.example;

import sun.security.provider.certpath.OCSPResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/customers")
public class CustomersRootResource {
  @DefaultValue("not defined") @HeaderParam("Simon-One") private String s1;
  @DefaultValue("not defined") @HeaderParam("Simon-Two") private String s2;
  private static final Map<Integer, String> MY_TABLE = new HashMap<>();
  static {
    MY_TABLE.put(1, "Fred, our first customer");
    MY_TABLE.put(3, "Jim, a loyal customer");
    MY_TABLE.put(92, "Sheila, buys lots of stuff");
    MY_TABLE.put(7, "Alan, nuisance, always returns stuff");
    MY_TABLE.put(16, "Mary, brings her friends to the store");
  }
  @GET
  @Path("/one/{path:.*}")
  @Produces(MediaType.TEXT_PLAIN)
  public String getSubStuff(@PathParam("path") String path) {
    return "This is funky path: " + path;
  }
  @GET
  @Path("/one")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCustOne() {
    return "This is customer one, name is Fred";
  }
  @GET
  @Path("/two")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCustTwo() {
    return "This is customer two, name is Jim";
  }
  @GET
  @Path("/{pk: \\d+}")
  @Produces(MediaType.TEXT_PLAIN)
  public Response getCustByPk(@PathParam("pk") int id) {
    Response.ResponseBuilder builder = Response.ok();
    String custInfo = MY_TABLE.get(id);
    String headerInfo = "header info is " + s1 + " " + s2;
    if (custInfo != null) {
      builder.entity(custInfo + headerInfo);
      builder.header("Simon-Was-Here","Ouch");
    } else {
      builder.entity("Er, who? I don't know customer id " + id + headerInfo);
      builder.status(Response.Status.NOT_FOUND);
      builder.header("Simon-Was-Here","Huh");
    }

    return builder.build();
  }
}
