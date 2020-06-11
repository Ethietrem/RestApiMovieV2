package pl.wsb.students.api;

import pl.wsb.students.api.handlers.ErrorHandler;
import pl.wsb.students.consts.ApiEndpoints;
import pl.wsb.students.exceptions.ValidationException;
import pl.wsb.students.model.LogOutUserRequest;
import pl.wsb.students.model.RegisterUserRequest;
import pl.wsb.students.model.UpdateUserRequest;

import pl.wsb.students.model.User;
import pl.wsb.students.repository.impl.UserAccountRepository;
import pl.wsb.students.security.annotation.Authenticate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiEndpoints.USER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource extends AbstractResource{
    @POST
    public Response postUser(RegisterUserRequest body) {
        try {
            UserAccountRepository userAccountRepository = new UserAccountRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    User.createFromUserAccount(
                            userAccountRepository.registerUser(body)
                    )
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
    @Authenticate
    @PUT
    @Path(ApiEndpoints.USER_ID_UPDATE)
    public Response putUser(UpdateUserRequest body) {
        try {
            UserAccountRepository userAccountRepository = new UserAccountRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    User.createFromUserAccount(
                            userAccountRepository.editUser(body)
                    )
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
    @Authenticate
    @PUT
    @Path(ApiEndpoints.USER_ID_LOGOUT)
    public Response putUserLogout(LogOutUserRequest body) {
        try {
            UserAccountRepository userAccountRepository = new UserAccountRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    User.createFromUserAccount(
                            userAccountRepository.logoutUser(body)
                    )
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
}
