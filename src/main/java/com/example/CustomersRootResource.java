package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customers")
public class CustomersRootResource {
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
  public String getCustByPk(@PathParam("pk") int id) {
    return "This is customer two, name is something else, Primary key requested is " + id;
  }
}
