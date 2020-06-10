package pl.wsb.students.api.impl;

import javax.ws.rs.client.Entity;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.*;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.wsb.students.api.AuthenticateResource;
import pl.wsb.students.model.MovieLibraryRequest;
import sun.security.util.SecurityConstants;

import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.security.Principal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class LibraryResourceTest extends JerseyTest {

    @Override
    protected Application configure(){
        final SecurityContext mockSecurityContext = mock(SecurityContext.class);
        when(mockSecurityContext.getUserPrincipal()).thenReturn(new Principal(){
            @Override
                    public String getName(){
                return "test@test.pl";
            }
        });
        ResourceConfig resourceConfig = new ResourceConfig(AuthenticateResource.class);
        resourceConfig.register(new ContainerRequestFilter() {
            @Override
            public void filter(ContainerRequestContext containerRequestContext) throws IOException {
                containerRequestContext.setSecurityContext(mockSecurityContext);
            }
        });
        return resourceConfig;
    }

    @Test
    public void givePostLibrary_whenCorrectCredentials_thenResponseIs200(){
        Response response = target("/library")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(
                        Entity.entity(
                                createProperRequestBody(),
                                MediaType.APPLICATION_JSON_TYPE
                        )
                );
        assertEquals(
                String.format("HTTP Response status code should be %s: ", Response.Status.OK.getStatusCode()),
                Response.Status.OK.getStatusCode(),
                response.getStatus()
        );
    }

    private MovieLibraryRequest createProperRequestBody() {
        return new MovieLibraryRequest();
    }
}
