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
    private String fakePosts;
    ReqRes main;

    @BeforeTest
    public void beforeTest() throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        restTemplate = new RestTemplate(requestFactory);
        headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        main = new ReqRes();
        fakePosts = "https://jsonplaceholder.typicode.com/posts";

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

    @Test (enabled = false)
    public void postUser(){
        String addURI = "https://reqres.in/api/users";
        String jsonBody = "{\"name\":\"Maxim\",\"job\":\"QA\"}";
        System.out.println("\n\n" + jsonBody);
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
        response = restTemplate.postForEntity(addURI, entity, String.class);
        responseBody = response.getBody();
        System.out.println(responseBody);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test (enabled = false)
    public void deleteUser(){
        String resourceToDelete = "https://reqres.in/api/users/2";
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        response = restTemplate.exchange(resourceToDelete, HttpMethod.DELETE, entity, String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);

    }

    @Test (enabled = false)
    public void putUser(){
        String resourceToUpdate = "https://reqres.in/api/users/2";
        String jsonBody = "{\"name\":\"Maxim\",\"job\":\"QA\"}";
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody,headers);
        response = restTemplate.exchange(resourceToUpdate, HttpMethod.PUT, entity, String.class);
        System.out.println(response.getBody());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test (enabled = false)
    public void testJsonValue() throws org.json.simple.parser.ParseException {
        String fooResourceUrl = "https://reqres.in/api/users/2";
        response = restTemplate.getForEntity(fooResourceUrl, String.class);
        responseBody = response.getBody();
        String lastName = main.getEmpIdFromResponse(responseBody, "data", "last_name");
        System.out.println(lastName);
        Assert.assertEquals(lastName, "Weaver");


        //HashMap<String, Map> answer = restTemplate.getForObject(fooResourceUrl, new HashMap<>().getClass());
        //answer.values().forEach(a -> System.out.println(a.toString()));
        //Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    //Linked CRUD tests
    @Test (enabled = false)
    public void createPost (){
        String jsonBody = "{\"userId\": 666,\"id\": 101, \"title\": \"evil\", \"body\": \"Don't touch my wife\" }";
        System.out.println("\n\n" + jsonBody);
        main.createPost(fakePosts, jsonBody);
    }

    @Test (enabled = false)
    public void getPost(){
        main.getPost(fakePosts, 100);

    }

    @Test (enabled = false)
    public void putPost(){
        String jsonBody = "{\"userId\": 10,\"id\": 100, \"title\": \"evil\", \"body\": \"Don't touch my wife\" }";
        System.out.println("\n\n" + jsonBody);
        main.putPost(fakePosts, 100, jsonBody );
    }

    @Test (enabled = false)
    public void deletePost(){
       main.deletePost(fakePosts, 999);
    }

    @Test
    public void checkValueFormGetResponse() throws org.json.simple.parser.ParseException {
        main.getPost(fakePosts, 6);
        //String titleOfId6 = main.getEmpIdFromResponse(responseBody, "title");
        String responseBody = main.getResponseBody(fakePosts, 6);
        String titleOfId6 = main.getEmpIdFromResponse(responseBody, "title");
        System.out.println(titleOfId6);
        Assert.assertEquals(titleOfId6, "dolorem eum magni eos aperiam quia");
    }






    @AfterTest
    public void afterTest() {
        restTemplate = new RestTemplate();
    }

}
