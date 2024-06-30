package api.test;

import api.endpoints.ActiveQuestsEndPoints;
import api.endpoints.CompletedQuestsEndPoints;
import api.payload.QuestsRecords;
import api.utilities.CommonTest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;


public class CompletedQuestActiveTests extends CommonTest {

    public Logger logger;

    @BeforeClass
    public void setupData() {
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testGetActiveQuestRecords() throws IOException {
        logger.info("*****  TEST GET ACTIVE QUEST RECORD  ****");

        QuestsRecords questsRecords = new QuestsRecords(10, 0);

        Response response = ActiveQuestsEndPoints.getActiveQuests(questsRecords);
        logger.info("*****  Response of testGetActiveQuestRecords ****");
        response.then().log().all();

        JSONObject expectedResponse = readExpectedJson("expectedGetActiveQuestRecords.json");
        JSONObject actualResponse = new JSONObject(response.body().asString());

        compareJsonObject(actualResponse, expectedResponse);
    }

    // just test the failed report
    @Test(priority = 2)
    public void testGetFailureActiveQuestRecords() throws IOException {
        logger.info("*****  TEST GET ACTIVE QUEST RECORD  ****");

        QuestsRecords questsRecords = new QuestsRecords(10, 0);

        Response response = ActiveQuestsEndPoints.getActiveQuests(questsRecords);
        logger.info("*****  Response of testGetActiveQuestRecords ****");
        response.then().log().all();

        JSONObject expectedResponse = readExpectedJson("expectedFailureGetActiveQuestRecords.json");
        JSONObject actualResponse = new JSONObject(response.body().asString());

        compareJsonObject(actualResponse, expectedResponse);
    }


}
