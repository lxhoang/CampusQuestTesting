package api.test;

import api.endpoints.TwitterProfileEndPoints;
import api.utilities.CommonTest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;


public class TwitterProfileTests extends CommonTest {

    public Logger logger;

    @BeforeClass
    public void setupData() {
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testGetTwitterProfile() throws IOException {
        logger.info("*****  TEST TWITTER PROFILE  ****");

        Response response = TwitterProfileEndPoints.getProfile();
        logger.info("*****  Response of testGetTwitterProfile ****");
        response.then().log().all();

        JSONObject expectedResponse = readExpectedJson("expectedGetTwitterProfile.json");
        JSONObject actualResponse = new JSONObject(response.body().asString());

        compareJson(actualResponse, expectedResponse);
    }





}
