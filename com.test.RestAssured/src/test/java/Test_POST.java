import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map;

public class Test_POST {

    @Test(enabled = false)
    public void test_1_POST(){

        //Map<String, Object> map = new HashMap<String, Object>();
        //map.put("name", "Maxim");
        //map.put("job", "QA");
        //JSONObject request = new JSONObject(map);

        JSONObject request = new JSONObject();
        request.put("name", "Maxim");
        request.put("job", "QA");

        given()
                .header("Content-Type", "application/json")
                .body(request.toJSONString())
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .statusCode(201);
    }

    @Test (enabled = false)
    public void test_2_PUT(){

        JSONObject request = new JSONObject();
        request.put("name", "Maxim");
        request.put("job", "QA");

        given()
                .header("Content-Type", "application/json")
                .body(request.toJSONString())
        .when()
                .put("https://reqres.in/api/users/2")
        .then()
                .statusCode(200).log().all();
    }
    @Test (enabled = false)
    public void test_3_PATCH(){

        JSONObject request = new JSONObject();
        request.put("name", "Maxim");
        request.put("job", "QA");

        given()
                .header("Content-Type", "application/json")
                .body(request.toJSONString())
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void test_4_DELETE(){

        given()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204).log().all();
    }
}
