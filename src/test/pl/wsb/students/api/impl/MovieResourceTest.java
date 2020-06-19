package pl.wsb.students.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
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

class MovieResourceTest {

    //test poprawnosci wywali 200
    @Test
    void postMovie_OK_200IsReceived() throws IOException {
        Map<String, String> map = new HashMap<String, String>() {
            {
                put("title", "nowy film o autach");  //// zeby nie było błedu trzeba zmienić nazwę
                put("year", "2000");
            }
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/api/v1/movie");
        StringEntity requestEntity = new StringEntity(
                requestBody,
                ContentType.APPLICATION_JSON
        );
        httpPost.setEntity(requestEntity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(httpPost);
        Assertions.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_OK);
        client.close();
    }

    //test poprawnosci wywali 400 bo brakuje wartości tytul w movie
    @Test
    void postMovie_BadRequest_400IsReceived() throws IOException {
        Map<String, String> map = new HashMap<String, String>() {
            {
                put("year", "2000");
            }
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/api/v1/movie");
        StringEntity requestEntity = new StringEntity(
                requestBody,
                ContentType.APPLICATION_JSON
        );
        httpPost.setEntity(requestEntity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(httpPost);
        Assertions.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_BAD_REQUEST);
        client.close();
    }
}