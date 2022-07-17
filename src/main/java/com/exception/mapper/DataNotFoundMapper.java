package com.exception.mapper;

import com.exception.DataNotFoundException;
import com.model.ErrorMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/* Throw this exception if resource is not found*/
@Provider
public class DataNotFoundMapper implements ExceptionMapper<DataNotFoundException> {


    @Override
    public Response toResponse(DataNotFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),404);

        return Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
    }
}
