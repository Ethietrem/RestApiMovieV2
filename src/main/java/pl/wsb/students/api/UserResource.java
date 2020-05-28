package pl.wsb.students.api;

import pl.wsb.students.consts.ApiEndpoints;
import pl.wsb.students.model.RegisterUserRequest;
import pl.wsb.students.model.UpdateUserRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiEndpoints.USER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource
{
    @POST
    public Response postUser(RegisterUserRequest body) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }
    @PUT
    @Path(ApiEndpoints.USER_ID_UPDATE)
    public Response putUser(UpdateUserRequest body) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }
}
