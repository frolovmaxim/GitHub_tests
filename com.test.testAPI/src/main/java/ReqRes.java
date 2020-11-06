import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;

public class ReqRes {
    private RestTemplate restTemplate;
    private ResponseEntity<String> response;
    private String responseBody;
    private HttpHeaders headers;

    public void setUp(){
        CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        restTemplate = new RestTemplate(requestFactory);
        headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
    }



    public String getEmpIdFromResponse(String jsonString, String firstKey) throws ParseException {
        JSONObject jsonObj;
        JSONParser parser = new JSONParser();  // parser to parse string to JSONObject
        jsonObj = (JSONObject) parser.parse(jsonString); // parse the Object using parse Method.
        String desiredObject = (String) jsonObj.get(firstKey);
        return desiredObject;
    }

    public String getEmpIdFromResponse(String json, String firstKey, String secondKey) {
        JSONParser parser = new JSONParser();
        Object obj = new Object();
        try {
            obj = parser.parse(json);
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonResponseObject = (JSONObject) obj;
        JSONObject data = (JSONObject) jsonResponseObject.get(firstKey);
        String aimValue = data.get(secondKey).toString();
        return aimValue;
    }

    public String getResponseBody(String url, int id){
        this.setUp();
        response = restTemplate.getForEntity(url + '/' + id, String.class);
        return response.getBody();
    }

    public void getPost(String url, int id){
        this.setUp();
        response = restTemplate.getForEntity(url + '/' + id, String.class);
        responseBody = response.getBody();
        System.out.println(responseBody);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    public void createPost(String url, String jsonBody){
        this.setUp();
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
        response = restTemplate.postForEntity(url, entity, String.class);
        responseBody = response.getBody();
        System.out.println(responseBody);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    public void putPost(String url, int id, String jsonBody){
        this.setUp();
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody,headers);
        response = restTemplate.exchange(url + '/' + id, HttpMethod.PUT, entity, String.class);
        System.out.println(response.getBody());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    public void deletePost(String url, int id){
        this.setUp();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        response = restTemplate.exchange(url + '/' + id, HttpMethod.DELETE, entity, String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
