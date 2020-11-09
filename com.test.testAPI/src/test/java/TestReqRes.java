import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestReqRes {
    private RestTemplate restTemplate;
    private ResponseEntity<String> response;
    private String responseBody;
    private HttpHeaders headers;
    private String fakePosts;
    private String reqResUrl;
    ReqRes main;

    @BeforeTest
    public void beforeTest() {
        main = new ReqRes();
        fakePosts = "https://jsonplaceholder.typicode.com/posts";
        reqResUrl = "https://reqres.in/api/users";
    }

    @Test
    public void getUser(){
        main.getPost(reqResUrl, 2);
        responseBody = main.getResponseBody(reqResUrl, 2);
        Assert.assertTrue(responseBody.contains("Janet"));
    }

    @Test
    public void postUser(){
        String jsonBody = "{\"name\":\"Maxim\",\"job\":\"QA\"}";
        main.createPost(reqResUrl, jsonBody);
    }

    @Test
    public void deleteUser(){
        main.deletePost(reqResUrl,2);
    }

    @Test
    public void putUser(){
        String jsonBody = "{\"name\":\"Maxim\",\"job\":\"QA\"}";
        main.putPost(reqResUrl, 2, jsonBody);
    }

    @Test
    public void testJsonValue() {
        main.getPost(reqResUrl, 2);
        responseBody = main.getResponseBody(reqResUrl, 2);
        String lastName = main.getEmpIdFromResponse(responseBody, "data", "last_name");
        System.out.println(lastName);
        Assert.assertEquals(lastName, "Weaver");
    }
    //Linked CRUD tests
    @Test
    public void createPost (){
        String jsonBody = "{\"userId\": 666,\"id\": 101, \"title\": \"evil\", \"body\": \"Don't touch my wife\" }";
        System.out.println("\n\n" + jsonBody);
        main.createPost(fakePosts, jsonBody);
    }

    @Test
    public void getPost(){
        main.getPost(fakePosts, 100);
    }

    @Test
    public void putPost(){
        String jsonBody = "{\"userId\": 10,\"id\": 100, \"title\": \"evil\", \"body\": \"Don't touch my wife\" }";
        System.out.println("\n\n" + jsonBody);
        main.putPost(fakePosts, 100, jsonBody );
    }

    @Test
    public void deletePost(){
       main.deletePost(fakePosts, 999);
    }

    @Test
    public void checkValueFormGetResponse() {
        main.getPost(fakePosts, 6);
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
