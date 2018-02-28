package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/customers")
public class CustomersRootResource {
  private static final Map<Integer, String> MY_TABLE = new HashMap<>();
  static {
    MY_TABLE.put(1, "Fred, our first customer");
    MY_TABLE.put(3, "Jim, a loyal customer");
    MY_TABLE.put(92, "Sheila, buys lots of stuff");
    MY_TABLE.put(1, "Alan, nuisance, always returns stuff");
    MY_TABLE.put(1, "Mary, brings her friends to the store");
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
  public String getCustByPk(@PathParam("pk") int id) {
    String custInfo = MY_TABLE.get(id);
    if (custInfo != null) {
      return custInfo;
    } else {
      return "Er, who? I don't know customer id " + id;
    }
  }
}
