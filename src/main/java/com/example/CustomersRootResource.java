package com.example;

import com.example.dto.CustomerDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/customers")
public class CustomersRootResource {
  @DefaultValue("not defined") @HeaderParam("Simon-One") private String s1;
  @DefaultValue("not defined") @HeaderParam("Simon-Two") private String s2;
  private static final Map<Integer, CustomerDTO> MY_TABLE = new HashMap<>();
  static {
    MY_TABLE.put(1, new CustomerDTO("Fred", "Here there and everywhere", 1234));
    MY_TABLE.put(3, new CustomerDTO("Jim", "nowhere man", 0));
    MY_TABLE.put(92, new CustomerDTO("Sheila", "On the hill", 20000));
    MY_TABLE.put(7, new CustomerDTO("Alan", "next door", 100));
    MY_TABLE.put(16, new CustomerDTO("Mary", "Over the rainbow", 400));
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
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public Response getCustByPk(@PathParam("pk") int id) {
    Response.ResponseBuilder builder = Response.ok();
    CustomerDTO custInfo = MY_TABLE.get(id);
    String headerInfo = "header info is " + s1 + " " + s2;
    if (custInfo != null) {
      builder.entity(custInfo);
      builder.header("Simon-Was-Here","Ouch");
    } else {
      builder.entity("Er, who? I don't know customer id " + id + headerInfo);
      builder.status(Response.Status.NOT_FOUND);
      builder.header("Simon-Was-Here","Huh");
    }

    return builder.build();
  }

  @POST
  @Path("/")
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response makeNewCustomer(CustomerDTO theEntity) {
    System.out.println("Got customer DTO: " + theEntity);
    theEntity.name = "Call me mister! " + theEntity.name;
    return Response.ok(theEntity).header("locationish", "Over the hill").build();
  }
}
