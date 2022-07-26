package com.services;

import com.businesslogic.UserAddressOperations;
import com.model.entities.UserAddress;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users/{userId}/address")
public class UserAddressService {
    private UserAddressOperations userAddressops;
    public UserAddressService(){
        userAddressops = new UserAddressOperations();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUserAddress(@PathParam("userId") int userId,UserAddress entity){
        entity.setUserId(userId);
        userAddressops.create(entity);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserAddress> getUserAddressById(@PathParam("userId") int userId){
        List<UserAddress> ue = userAddressops.findByUser(userId);


        return ue;
    }
}
