package com.services;

import com.businesslogic.UserAddressOperations;
import com.model.entities.UserAddress;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users/address")
public class UserAddressService {
    private UserAddressOperations userAddressops;
    public UserAddressService(){
        userAddressops = new UserAddressOperations();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUserAddress(UserAddress entity){
        userAddressops.create(entity);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserAddress getUserAddressById(@PathParam("id") int id){
        UserAddress ue = userAddressops.find(id);
        return ue;
    }
}
