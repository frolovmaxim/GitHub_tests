import org.apache.http.ParseException;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;


public class TestReqRes {
    private RestTemplate restTemplate;
    private ResponseEntity<String> response;
    private String responseBody;
    private HttpHeaders headers;

    @BeforeTest
    public void beforeTest() throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        restTemplate = new RestTemplate(requestFactory);
        headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
    }

    @Test //(enabled = false)
    public void getUser(){

        String fooResourceUrl = "https://reqres.in/api/users/2";
        response = restTemplate.getForEntity(fooResourceUrl, String.class);
        responseBody = response.getBody();
        System.out.println("GET Response Body :" + responseBody);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertTrue(responseBody.contains("Janet"));

    }

    @Test //(enabled = false)
    public void postUser(){
        String addURI = "https://reqres.in/api/users";
        //HttpHeaders headers = new HttpHeaders();
        //headers.add("Accept", "application/json");
        //headers.add("Content-Type", "application/json");

        String jsonBody = "{\"name\":\"Maxim\",\"job\":\"QA\"}";
        System.out.println("\n\n" + jsonBody);
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
        response = restTemplate.postForEntity(addURI, entity, String.class);
        responseBody = response.getBody();
        System.out.println(responseBody);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test //(enabled = false)
    public void deleteUser(){
        String resourceToDelete = "https://reqres.in/api/users/2";
        //HttpHeaders headers = new HttpHeaders();
        //headers.add("Accept", "application/json");
        //headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        response = restTemplate.exchange(resourceToDelete, HttpMethod.DELETE, entity, String.class);
        //restTemplate.delete(resourceToDelete);
        //response = restTemplate.getForEntity(resourceToDelete, String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);

    }

    @Test
    public void putUser(){
        String resourceToUpdate = "https://reqres.in/api/users/2";
        //HttpHeaders headers = new HttpHeaders();
        //headers.add("Accept", "application/json");
        //headers.add("Content-Type", "application/json");
        String jsonBody = "{\"name\":\"Maxim\",\"job\":\"QA\"}";
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody,headers);
        response = restTemplate.exchange(resourceToUpdate, HttpMethod.PUT, entity, String.class);
        System.out.println(response.getBody());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }


    @AfterTest
    public void afterTest() {
        restTemplate = new RestTemplate();
    }

}
