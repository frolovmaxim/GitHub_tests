import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class ReqRes {



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

}
