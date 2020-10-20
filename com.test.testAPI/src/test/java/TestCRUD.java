
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.io.IOException;
import java.text.ParseException;

@SuppressWarnings("ALL")
public class TestCRUD {
        private String responseBody;
        public String responseBodyPOST;
        final static Logger logger = Logger.getLogger(TestCRUD.class);
        //RESTTemplate Object
        private RestTemplate restTemplate;

        //Employee ID
        private String employeeId;
        // Create Response Entity - Stores HTTPStatus Code, Response Body, etc
        private ResponseEntity<String> response;

        @BeforeTest
        public void beforeTest() throws IOException, ParseException {
            logger.info("Setting up prerequisite for test execution");
            logger.info("Creating RestTemplate object before tests");
            this.restTemplate = new RestTemplate();
        }

        /**
         * Test Method to add employee using HTTP POST request
         *
         * Verifies POST action Status Code
         *
         * @throws IOException
         * @throws ParseException
         */
        @Test
        public void addEmployee() throws IOException, ParseException, org.json.simple.parser.ParseException {
            String addURI = "http://dummy.restapiexample.com/api/v1/create";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");

            logger.info("Add URL :"+addURI);
            String jsonBody = "{\"name\":\"zozo100\",\"salary\":\"123\",\"age\":\"23\"}";
            System.out.println("\n\n" + jsonBody);
            HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);

            //POST Method to Add New Employee
            this.response = this.restTemplate.postForEntity(addURI, entity, String.class);
            this.responseBodyPOST = this.response.getBody().toString();
            // Write response to file
            this.responseBody = this.response.getBody();
            System.out.println("responseBody --->" + this.responseBody);
            // Get ID from the Response object
            this.employeeId = getEmpIdFromResponse(this.responseBody);
            System.out.println("empId is :" + this.employeeId);
            // Check if the added Employee is present in the response body.
            Assert.assertTrue(this.responseBody.contains(this.employeeId));
            // System.out.println(propertyFile.get("EmployeeAddResBody"));
            // Check if the status code is 201
            Assert.assertEquals(this.response.getStatusCode(), HttpStatus.OK);
            logger.info("Employee is Added successfully employeeId:" + this.employeeId);
        }

    /**
         * Method to get Employee ID from REsponse body
         * I have used Json Simple API for Parsing the JSON object
         *
         * @return
         */
        public static String getEmpIdFromResponse() throws org.json.simple.parser.ParseException {
            return getEmpIdFromResponse();
        }

    /**
         * Method to get Employee ID from REsponse body
         * I have used Json Simple API for Parsing the JSON object
         *
         * @param json
         * @return
         */
        public static String getEmpIdFromResponse(String json) throws org.json.simple.parser.ParseException {
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
            String id = (String) data.get("id").toString();

            return id;
        }
        /**
         * Test Method to Update employee using HTTP PUT request
         *
         * Verifies PUT action Status Code
         * Verifies Updated Name exists in Response Body
         *
         * @throws IOException
         * @throws ParseException
         */
        @Test(dependsOnMethods = "addEmployee", enabled = true)
        public void  updateEmployee() throws IOException, ParseException {
            String updateURI = "http://dummy.restapiexample.com/api/v1/update/" + this.employeeId;
            logger.info("Update URL :" + updateURI);

            String jsonBody = this.responseBody;


            jsonBody = jsonBody.replace("zozo100", "update_zozo100");

            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);

            //PUT Method to Update the existing Employee
            //NOTE that I have Not used restTemplate.put as it's void and we need response for verification
            this.response = this.restTemplate.exchange(updateURI, HttpMethod.PUT, entity, String.class);
            this.responseBody = this.response.getBody();
            System.out.println("Update Response Body :" + responseBody);

            // Check if the updated Employee is present in the response body.
            Assert.assertTrue(this.responseBody.contains("update_zozo100"));

            // Check if the status code is 200
            Assert.assertEquals(this.response.getStatusCode(), HttpStatus.OK);

            logger.info("Employee Name is Updated successfully employeeId:" + this.employeeId);

        }

        /**
         * Test Method to Get employee using HTTP GET request
         *
         * Verifies GET action Status Code
         * Verifies Name exists in Response Body
         *
         * @throws IOException
         * @throws ParseException
         */
        @Test(dependsOnMethods = "updateEmployee", enabled = true)
        void getEmployee() throws IOException, ParseException {
            employeeId = "23";
            String getURI = "http://dummy.restapiexample.com/api/v1/employee/" + employeeId;
            logger.info("Get URL :" + getURI);

            //HttpHeaders headers = new HttpHeaders();
            //HttpEntity<String> entity = new HttpEntity<String>(headers);

            //GET Method to Get existing Employee
            response = restTemplate.getForEntity(getURI,String.class);

            // Write response to file
            responseBody = response.getBody();

            //Suppressing for log diffs
            System.out.println("GET Response Body :" + responseBody);


            // Check if the added Employee ID is present in the response body.
            Assert.assertTrue(responseBody.contains("Caesar Vance"));

            // Check if the status code is 200
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

            logger.info("Employee is retrieved successfully employeeId:" + employeeId);

        }

        /**
         * Test Method to Delete employee using HTTP DELETE request
         *
         * Verifies DELETE action Status Code
         * Verifies Success Message Text in Response Body
         *
         * @throws IOException
         * @throws ParseException
         */
        @Test(dependsOnMethods = "getEmployee", enabled = true)
        public void deleteEmployee() throws IOException, ParseException {
            employeeId = "23";
            String delURI = "http://dummy.restapiexample.com/api/v1/delete/" + employeeId;
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<String>(headers);

            //DELETE Method to Delete existing Employee
            response = restTemplate.exchange(delURI, HttpMethod.DELETE, entity, String.class);

            // Check if the status code is 204
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

            responseBody = response.getBody();

            Assert.assertEquals(getMessageFromResponse(responseBody), "Successfully! Record has been deleted");

            logger.info("Employee is Deleted successfully employeeId:" + employeeId);
        }

        /**
         * Gets "text" key value from Response body text for verification
         * I have used Json Simple API for Parsing the JSON object
         *
         * @param json
         * @return text string
         */
        public static String getMessageFromResponse(String json) {
            String successMessage = null;
            String successMessageText = null;
            try {
                JSONParser parser = new JSONParser();

                JSONObject jsonResponseObject = (JSONObject) (parser.parse(json));
                successMessage = jsonResponseObject.get("message").toString();

                //jsonResponseObject = (JSONObject) (parser.parse(successMessage));
                //successMessageText = jsonResponseObject.get("text").toString();
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
            return successMessage;
        }

        @AfterTest
        public void afterTest() {
            logger.info("Clean up after test execution");
            logger.info("Creating RestTemplate object as Null");
            this.restTemplate = new RestTemplate();
        }
}






