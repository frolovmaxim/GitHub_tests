import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestReqRes {
    private RestTemplate restTemplate;
    private String responseBody;
    private String fakePosts;
    private String reqResUrl;
    ReqRes main;

    @BeforeTest
    public void beforeTest() {
        main = new ReqRes();
        fakePosts = "https://jsonplaceholder.typicode.com/posts";
        reqResUrl = "https://reqres.in/api/users";
    }

    //reqres.in

    @Test (enabled = false)
    public void getUser(){
        main.getPost(reqResUrl, 2);
        responseBody = main.getResponseBody(reqResUrl, 2);
        Assert.assertTrue(responseBody.contains("Janet"));
    }

    @Test (enabled = false)
    public void postUser(){
        String jsonBody = "{\"name\":\"Maxim\",\"job\":\"QA\"}";
        main.createPost(reqResUrl, jsonBody);
    }

    @Test (enabled = false)
    public void deleteUser(){
        main.deletePost(reqResUrl,2);
    }


    @Test(dataProvider = "users", dataProviderClass = DataProviderClass.class)
    public void putUser(String data){
        String jsonBody = data;
        main.putPost(reqResUrl, 2, jsonBody);
    }

    @Test (enabled = false)
    public void testJsonValue() {
        main.getPost(reqResUrl, 2);
        responseBody = main.getResponseBody(reqResUrl, 2);
        String lastName = main.getEmpIdFromResponse(responseBody, "data", "last_name");
        System.out.println(lastName);
        Assert.assertEquals(lastName, "Weaver");
    }

    //jsonplaceholder.typicode.com

    @Test (enabled = false)
    public void createPost (){
        String jsonBody = "{\"userId\": 666,\"id\": 101, \"title\": \"evil\", \"body\": \"Don't touch my wife\" }";
        main.createPost(fakePosts, jsonBody);
    }

    @Test (enabled = false)
    public void getPost(){
        main.getPost(fakePosts, 100);
    }

    @Test (enabled = false)
    public void putPost(){
        String jsonBody = "{\"userId\": 10,\"id\": 100, \"title\": \"evil\", \"body\": \"Don't touch my wife\" }";
        main.putPost(fakePosts, 100, jsonBody );
    }

    @Test (enabled = false)
    public void deletePost(){
       main.deletePost(fakePosts, 999);
    }

    @Test (enabled = false)
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
