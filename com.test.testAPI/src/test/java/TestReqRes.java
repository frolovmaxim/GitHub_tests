import org.apache.http.ParseException;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @BeforeTest
    public void beforeTest() throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        restTemplate = new RestTemplate(requestFactory);
    }

    @Test (enabled = false)
    public void getUser(){

        String fooResourceUrl = "https://reqres.in/api/users/2";
        response = restTemplate.getForEntity(fooResourceUrl, String.class);
        responseBody = response.getBody();
        System.out.println("GET Response Body :" + responseBody);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertTrue(responseBody.contains("Janet"));

    }

    @Test
    public void postUser(){
        String addURI = "https://reqres.in/api/users";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");

        String jsonBody = "{\"name\":\"Maxim\",\"job\":\"QA\"}";
        System.out.println("\n\n" + jsonBody);
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
        response = restTemplate.postForEntity(addURI, entity, String.class);
        responseBody = response.getBody();
        System.out.println(responseBody);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }


    @AfterTest
    public void afterTest() {
        restTemplate = new RestTemplate();
    }

}