package api.test;

import api.endpoints.*;
import api.utilities.CommonTest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class GetReferralBalancePointTests extends CommonTest {

    public Logger logger;

    @BeforeClass
    public void setupData() {
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void getReferralBalancePoints() {
        logger.info("*****  TEST getReferralBalancePoints  ****");

        Response response = BalanceReferralPointsEndPoints.getBalancePoints();
        logger.info("*****  Response of getBalanceOCPoints ****");
        response.then().log().all();

        JSONObject expectedResponse = readExpectedJson("expectedReferralPointBalance.json");
        JSONObject actualResponse = new JSONObject(response.body().asString());

        compareJsonObject(actualResponse, expectedResponse);
    }





}
