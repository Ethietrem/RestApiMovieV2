package pl.wsb.students.api;

import pl.wsb.students.consts.ApiEndpoints;
import pl.wsb.students.hibernatemodel.UserAccount;
import pl.wsb.students.model.MovieDeleteRequest;
import pl.wsb.students.model.MovieLibraryRequest;
import pl.wsb.students.security.annotation.Authenticate;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path(ApiEndpoints.LIBRARY)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LibraryResource {

    @Context
    SecurityContext securityContext;

    @Authenticate
    @POST
    public Response postLibrary(MovieLibraryRequest body) {
        //UserAccount userAccount = SecurityContextHandler.getUserAccount(securityContext);
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }

    //******************************************************************************************************************
    @Authenticate
    @DELETE
    public Response deleteLibrary(MovieDeleteRequest body) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }
}