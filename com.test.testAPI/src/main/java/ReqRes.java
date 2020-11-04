import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;

public class ReqRes {
    private RestTemplate restTemplate;
    private ResponseEntity<String> response;
    private String responseBody;
    private HttpHeaders headers;

    public ReqRes setUp (){
        CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        restTemplate = new RestTemplate(requestFactory);
        headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        return this;
    }



    public String getEmpIdFromResponse(String json) throws org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonResponseObject = new JSONObject();
        Object obj = new Object();
        try {
            obj = parser.parse(json);
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        jsonResponseObject = (JSONObject) obj;

        JSONObject data = (JSONObject) jsonResponseObject.get("data");
        String lastName = data.get("last_name").toString();

        return lastName;
    }

    public void getPosts1 (String url, int id, HttpStatus status){
        response = restTemplate.getForEntity(url + '/' + id, String.class);
        responseBody = response.getBody();
        System.out.println(responseBody);
        Assert.assertEquals(response.getStatusCode(), status);
    }

}
