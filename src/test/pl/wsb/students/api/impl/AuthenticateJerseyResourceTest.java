package pl.wsb.students.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import pl.wsb.students.api.AuthenticateResource;
import pl.wsb.students.model.AuthenticationResponse;
import pl.wsb.students.model.AuthenticationRequest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AuthenticateJerseyResourceTest extends JerseyTest {

    @Override
    protected Application configure() {return new ResourceConfig(AuthenticateResource.class); }

    //to jest jakby kontener zawierający wszyskir endopinty i on sb sprawdza wszystko
    @Test
    public void givenPostAuthenticate_whenCorrectCredentials_thenResponseIs200(){
        Response response = target("/authenticate")
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

    @Test
    public void givenPostAuthenticate_whenIncorrectCredentials_thenResponseIs400(){
        Response response = target("/authenticate")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(
                        Entity.entity(
                                createImproperRequestBody(),
                                MediaType.APPLICATION_JSON_TYPE
                        )
                );
        assertEquals(
                String.format("HTTP Response status code should be %s: ", Response.Status.UNAUTHORIZED.getStatusCode()),
                Response.Status.UNAUTHORIZED.getStatusCode(),
                response.getStatus()
        );
    }

    @Test
    public void givenPostAuthenticate_whenCorrectRequest_thenResponseContainsAuteehResponse() throws IOException{
        Response response = target("/authenticate")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(
                        Entity.entity(
                                createImproperRequestBody(),
                                MediaType.APPLICATION_JSON_TYPE
                        )
                );
        String content = response.readEntity(String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(
                "HTTP Response object type: ",
                AuthenticationResponse.class,
                objectMapper.readValue(content, AuthenticationResponse.class).getClass()
        );
    }

    private AuthenticationRequest createProperRequestBody(){
        return new AuthenticationRequest()
                .email("test@test.pl")
                .password("test");
    }

    private AuthenticationRequest createImproperRequestBody(){
        return new AuthenticationRequest()
                .email("test@test.pl")
                .password("zle");
    }
}
