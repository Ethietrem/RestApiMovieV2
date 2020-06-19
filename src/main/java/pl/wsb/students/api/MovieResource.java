package pl.wsb.students.api;

import pl.wsb.students.api.handlers.ErrorHandler;
import pl.wsb.students.api.handlers.SecurityContextHandler;
import pl.wsb.students.consts.ApiEndpoints;
import pl.wsb.students.exceptions.UnauthenticatedException;
import pl.wsb.students.exceptions.ValidationException;
import pl.wsb.students.hibernatemodel.Movie;
import pl.wsb.students.hibernatemodel.UserAccount;
import pl.wsb.students.model.MovieCommentRequest;
import pl.wsb.students.model.MovieResponse;
import pl.wsb.students.model.MovieRatingRequest;
import pl.wsb.students.model.MovieRequest;
import pl.wsb.students.repository.impl.MovieCommentRepository;
import pl.wsb.students.repository.impl.MovieRepository;
import pl.wsb.students.repository.impl.UserAccountRepository;
import pl.wsb.students.security.annotation.Authenticate;

import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path(ApiEndpoints.MOVIE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource extends AbstractResource{

    //to jest od @Authenticate, czy jestem zalogowany
    @Context
    SecurityContext securityContext;

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
        //UserAccount userAccount = SecurityContextHandler.getUserAccount(securityContext);
        try{
            MovieRepository movieRepository = new MovieRepository();//tworzymy obiekt usera i sprawdzenie czy taki email istnieje
            Movie movie = movieRepository.findByName(body.getTitle());
            if (movie != null) {
                throw new UnauthenticatedException();
            } //jesli nie ma to nie sprawdzaj dalej
            return Response.status(
                    Response.Status.OK
            ).entity(
                    MovieResponse.createMovie(
                            movieRepository.addMovie(body)
                    )
            ).build();

        }catch (UnauthenticatedException ex) {
            return Response.status(
                    Response.Status.FORBIDDEN
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }catch (ValidationException ex){
            return Response.status(
                    Response.Status.BAD_REQUEST
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }

    //@Authenticate
    @POST
    @Path(ApiEndpoints.MOVIE_RATE)//dodanie anotacji Path dla niektórych metod w celu uwzględnienia zagnieżdżonych urli
    public Response postMovieRate(MovieRatingRequest body) {
        UserAccount userAccount = SecurityContextHandler.getUserAccount(securityContext);
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
    public Response postMovieComment(MovieCommentRequest body) {
        Persistence.createEntityManagerFactory("manager").createEntityManager();
        UserAccount userAccount = SecurityContextHandler.getUserAccount(securityContext);
        try{
            MovieCommentRepository commentRepository = new MovieCommentRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    MovieResponse.createComment(
                            commentRepository.addMovieComment(body)
                    )
            ).build();
        }catch (ValidationException ex){
            return Response.status(
                    Response.Status.BAD_REQUEST
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }
}