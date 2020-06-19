package pl.wsb.students.security.annotation;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import pl.wsb.students.api.handlers.ErrorHandler;
import pl.wsb.students.exceptions.UnauthenticatedException;
import pl.wsb.students.hibernatemodel.ApiToken;
import pl.wsb.students.hibernatemodel.UserAccount;
import pl.wsb.students.repository.EntityManagerHelper;
import pl.wsb.students.repository.impl.ApiTokenRepository;
import pl.wsb.students.security.MovieAppSecurityContext;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.util.Date;

/*
• w filtrze pobieramy wartość tokena z nagłówka,

• jeżeli ta operacja się powiedzie, walidujemy istnienie tokena oraz jego ważność w bazie danych,

• jeżeli się uda, wydłużamy ważność tokena o założony czas trania sesji, np. 30 minut,

• jeżeli się nie uda, filtr przerywa żądanie oraz zwraca błąd braku uwierzytelnienia;

 */
@Authenticate
@Provider//miejsce obsługi adnotacji- dostawca obsługi
@Priority(Priorities.AUTHENTICATION)
public class AuthenticateFilter implements ContainerRequestFilter {

    @Context
    UriInfo uriInfo;

    //filt włącza się z kazdym zadaniem bo jest w web,xml wpisany
    //sprawdzany jest tu autoryzacja konta
    @Override
    public void filter(ContainerRequestContext requestContext) {
        final String AUTH_HEADER_BEARER = "B:";
        try {
            String authorizationHeader =
                    requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isBlank(authorizationHeader)) {
                throw new UnauthenticatedException("No authorization data provided...");
            }
            if (!authorizationHeader.startsWith(AUTH_HEADER_BEARER)) {
                throw new UnauthenticatedException("Improper format of authorization data...");
            }//if
            requestContext.setSecurityContext(
                    new MovieAppSecurityContext(
                            validateToken(
                                    authorizationHeader.substring(AUTH_HEADER_BEARER.length()).trim()
                            ),
                            uriInfo
                    )
            );
        } catch (UnauthenticatedException ex) {
            requestContext.abortWith(

                    Response.status(Response.Status.UNAUTHORIZED).entity(ErrorHandler.getErrorResponse(ex)).build()
            );
        }
    }

    //walidacja tokena
    //sprawdzanie czy token w bazie
    private UserAccount validateToken(String accessToken) throws UnauthenticatedException
    {
        final int TOKEN_VALIDATION_MINUTES = 30;
        if (StringUtils.isBlank(accessToken)) {
            throw new UnauthenticatedException();
        } //if
        ApiToken apiToken = ApiTokenRepository.findByAccessToken(accessToken);
        if (apiToken == null) {
            throw new UnauthenticatedException();
        } //if
        if (apiToken.getUserAccount() == null) {
            throw new UnauthenticatedException();
        } //if
        EntityManagerHelper.startTransaction();
        ApiTokenRepository tokenRepository = new ApiTokenRepository();
        apiToken.setValidTo(
                DateUtils.addMinutes(
                        new Date(),
                        TOKEN_VALIDATION_MINUTES
                )
        );
        tokenRepository.merge(apiToken);
        EntityManagerHelper.commitTransaction();
        return apiToken.getUserAccount();
    }
}