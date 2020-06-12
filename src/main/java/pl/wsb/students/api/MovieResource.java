package pl.wsb.students.api;

import org.apache.commons.lang3.StringUtils;
import pl.wsb.students.api.handlers.ErrorHandler;
import pl.wsb.students.consts.ApiEndpoints;
import pl.wsb.students.exceptions.UnauthenticatedException;
import pl.wsb.students.exceptions.ValidationException;
import pl.wsb.students.model.Movie;
import pl.wsb.students.model.MovieRatingRequest;
import pl.wsb.students.model.MovieRequest;
import pl.wsb.students.repository.impl.MovieRepository;
import pl.wsb.students.security.annotation.Authenticate;

import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.reflections.util.ConfigurationBuilder.build;

@Path(ApiEndpoints.MOVIE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource extends AbstractResource{
    @GET
    public Response getMovie(
            @QueryParam(ApiEndpoints.PARAM_LIMIT) Integer limit,
            @QueryParam(ApiEndpoints.PARAM_OFFSET) Integer offset,
            @QueryParam(ApiEndpoints.PARAM_SEARCH) String search
    ) {
        //to jest do testowania endpointow na koniec 2 lab do sprawdzenia poprawności mapowań itp
        //Persistence.createEntityManagerFactory("manager").createEntityManager();
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }

    //@Authenticate
    @POST
    public Response postMovie(MovieRequest body) {
        Persistence.createEntityManagerFactory("manager").createEntityManager();
        try{
            MovieRepository movieRepository = new MovieRepository();
            return Response.status(Response.Status.OK).entity("mock call ok...").build();
            /*return Response.status(
                    Response.Status.OK
            ).entity(
                    Movie.createMovie(
                            movieRepository.addMovie(body)
                    )
            ).build();*/

        /*}catch (ValidationException ex){
            return Response.status(
                    Response.Status.BAD_REQUEST
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();*/
        }catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }

    @Authenticate
    @POST
    @Path(ApiEndpoints.MOVIE_RATE)//dodanie anotacji Path dla niektórych metod w celu uwzględnienia zagnieżdżonych urli
    public Response postMovieRate(MovieRatingRequest body) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }

    @Authenticate
    @PUT
    @Path(ApiEndpoints.MOVIE_ID_ACCEPT)
    public Response putMovieIdAccept(Integer id) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }

    @Authenticate
    @PUT
    @Path(ApiEndpoints.MOVIE_ID_REJECT)
    public Response putMovieIdReject(Integer id) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }

    //******************************************************************************************************************
    //@Authenticate
    @POST
    @Path(ApiEndpoints.MOVIE_COMMENT)
    public Response postMovieComment(MovieRatingRequest body) {
        Persistence.createEntityManagerFactory("manager").createEntityManager();
        try{
            MovieRepository movieRepository = new MovieRepository();
            /*return Response.status(
                    Response.Status.OK
            ).entity(
                    Movie.createFromMovie(
                            movieRepository.addMovie(body)
                    )
            ).build();*/
            return Response.status(Response.Status.OK).entity("mock call ok...").build();
        /*}catch (ValidationException ex){
            return Response.status(
                    Response.Status.BAD_REQUEST
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();*/
        }catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }
}