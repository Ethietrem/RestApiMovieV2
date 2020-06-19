package pl.wsb.students.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserResourceTest {

    @Test
    void postUser_putUser_405IsReceived() throws IOException {
        Map<String, String> map = new HashMap<String, String>() {
            {
                put("email", "test@test.pl");
                put("password", "12345");
            }
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut("http://127.0.0.1:8080/api/v1/user");
        StringEntity requestEntity = new StringEntity(
                requestBody,
                ContentType.APPLICATION_JSON
        );
        httpPut.setEntity(requestEntity);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(httpPut);
        Assertions.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_METHOD_NOT_ALLOWED);
        client.close();
    }

    @Test
    void postUser_BadRequest_400IsReceived() throws IOException {
        Map<String, String> map = new HashMap<String, String>() {
            {
                put("email", "test123@test123.pl");
                put("password", "test123");
            }
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPostt = new HttpPost("http://127.0.0.1:8080/api/v1/user");
        StringEntity requestEntity = new StringEntity(
                requestBody,
                ContentType.APPLICATION_JSON
        );
        httpPostt.setEntity(requestEntity);
        httpPostt.setHeader("Accept", "application/json");
        httpPostt.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(httpPostt);
        Assertions.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_BAD_REQUEST);
        client.close();
    }

    @Test
    void postUser_OK_200IsReceived() throws IOException {
        Map<String, String> map = new HashMap<String, String>() {
            {
                put("email", "NowyTest12345@NowyTest12345.pl");//////////////aby test był ok dodać coś do emaila
                put("password", "NowyTest12345");
            }
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPostt = new HttpPost("http://127.0.0.1:8080/api/v1/user");
        StringEntity requestEntity = new StringEntity(
                requestBody,
                ContentType.APPLICATION_JSON
        );
        httpPostt.setEntity(requestEntity);
        httpPostt.setHeader("Accept", "application/json");
        httpPostt.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(httpPostt);
        Assertions.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_OK);
        client.close();
    }

    @Test
    void putUser_Logout_200IsReceived() throws IOException {
        Map<String, String> map = new HashMap<String, String>() {
            {
                put("email", "test@test.pl");
            }
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut("http://127.0.0.1:8080/api/v1/user/logout");
        StringEntity requestEntity = new StringEntity(
                requestBody,
                ContentType.APPLICATION_JSON
        );
        httpPut.setEntity(requestEntity);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(httpPut);
        Assertions.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_OK);
        client.close();
    }
}