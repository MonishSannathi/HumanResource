package com.services;

import com.businesslogic.UserOperations;
import com.exception.DataNotFoundException;
import com.model.UserSummary;
import com.model.entities.UserEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
public class UserService {
    private UserOperations userOps;

    public UserService(){
        userOps = new UserOperations();
    }
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserEntity getUserById(@PathParam("id") int id){
        return userOps.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserEntity entity){
        userOps.create(entity);

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserById(UserEntity entity){
        userOps.update(entity);

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUserById(@PathParam("id") int id){
        userOps.removeUserById(id);

        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("summary")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserSummary> getUserSummary(){
        return userOps.getUserSummary();
    }

}
