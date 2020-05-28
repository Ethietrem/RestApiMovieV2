package pl.wsb.students.api;

import pl.wsb.students.consts.ApiEndpoints;
import pl.wsb.students.model.MovieDeleteRequest;
import pl.wsb.students.model.MovieLibraryRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiEndpoints.LIBRARY)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LibraryResource {
    @POST
    public Response postLibrary(MovieLibraryRequest body) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }
    @DELETE
    public Response deleteLibrary(MovieDeleteRequest body) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }
}