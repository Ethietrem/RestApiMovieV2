package pl.wsb.students.api;

import pl.wsb.students.api.handlers.ErrorHandler;
import pl.wsb.students.consts.ApiEndpoints;
import pl.wsb.students.exceptions.ApiException;
import pl.wsb.students.exceptions.UnauthenticatedException;
import pl.wsb.students.exceptions.ValidationException;
import pl.wsb.students.hibernatemodel.UserAccount;
import pl.wsb.students.exceptions.UnauthenticatedException;
import pl.wsb.students.model.*;

import pl.wsb.students.repository.impl.ApiTokenRepository;
import pl.wsb.students.repository.impl.UserAccountRepository;
import pl.wsb.students.security.annotation.Authenticate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.reflections.util.ConfigurationBuilder.build;

@Path(ApiEndpoints.USER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource extends AbstractResource{
    @POST
    public Response postUser(RegisterUserRequest body) {
        try {
            if (body == null) {
                throw new ValidationException("No request data provided...");
            }
            UserAccountRepository userAccountRepository = new UserAccountRepository();//tworzymy obiekt usera i sprawdzenie czy taki email istnieje
            UserAccount userAccount = userAccountRepository.findByEmail(body.getEmail());
            if (userAccount == null) {
                throw new UnauthenticatedException();
            } //jesli nie ma to nie autoryzujemy
            return Response.status(
                    Response.Status.OK
            ).entity(
                    User.createFromUserAccount(
                            userAccountRepository.registerUser(body)
                    )
            ).build();
        } catch (UnauthenticatedException ex) {
            return Response.status(
                    Response.Status.UNAUTHORIZED
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        } catch (ValidationException ex) {
            return Response.status(
                    Response.Status.BAD_REQUEST
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        } catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }

    //******************************************************************************************************************
    //@Authenticate
    @PUT
    @Path(ApiEndpoints.USER_UPDATE)
    public Response putUser(UpdateUserRequest body) {// tu jest problem 500internalServerError hibernatemodel.UserAccount>passHash
        try {
            if (body == null) {
                throw new ValidationException("No request data provided...");
            }
            UserAccountRepository userAccountRepository = new UserAccountRepository();//tworzymy obiekt usera i sprawdzenie czy taki email istnieje
            UserAccount userAccount = userAccountRepository.findByEmail(body.getEmail());
            if (userAccount == null) {
                throw new UnauthenticatedException();
            } //jesli nie ma to nie autoryzujemy
            return Response.status(
                    Response.Status.OK
            ).entity(
                    User.editUserAccount(
                            userAccountRepository.editUser(body)
                    )
            ).build();
        } catch (UnauthenticatedException ex) {
            return Response.status(
                    Response.Status.UNAUTHORIZED
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        } catch (ValidationException ex) {
            return Response.status(
                    Response.Status.BAD_REQUEST
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        } catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }

    //******************************************************************************************************************
    //@Authenticate
    @PUT
    @Path(ApiEndpoints.USER_LOGOUT)
    public Response putUserLogout(AuthenticationRequest body) {
        try {
            if (body == null) {
                throw new ValidationException("No request data provided...");
            }
            UserAccountRepository userAccountRepository = new UserAccountRepository();//tworzymy obiekt usera i sprawdzenie czy taki email istnieje
            UserAccount userAccount = userAccountRepository.findByEmail(body.getEmail());
            if (userAccount == null) {
                throw new UnauthenticatedException();
            } //jesli nie ma to nie autoryzujemy
            ApiTokenRepository apiTokenRepository = new ApiTokenRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    AuthenticationResponse.logoutFromApiToken(
                            apiTokenRepository.generateApiToken(
                                    userAccount
                            )
                    )
            ).build();
        } catch (UnauthenticatedException ex) {
            return Response.status(
                    Response.Status.UNAUTHORIZED
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        } catch (ApiException ex) {
            return Response.status(
                    Response.Status.BAD_REQUEST
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        } catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }
}
