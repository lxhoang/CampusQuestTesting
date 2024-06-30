package api.test;

import api.endpoints.CompletedQuestsEndPoints;
import api.endpoints.TwitterProfileEndPoints;
import api.payload.QuestsRecords;
import api.utilities.CommonTest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;


public class CompletedQuestRecordsTests extends CommonTest {

    public Logger logger;

    @BeforeClass
    public void setupData() {
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testGetCompletedQuestRecords() throws IOException {
        logger.info("*****  TEST GET COMPLETED QUEST RECORD  ****");

        QuestsRecords questsRecords = new QuestsRecords(10, 0);

        Response response = CompletedQuestsEndPoints.getCompletedQuests(questsRecords);
        logger.info("*****  Response of testGetCompletedQuestRecords ****");
        response.then().log().all();

        JSONObject expectedResponse = readExpectedJson("expectedGetCompletedQuestRecords.json");
        JSONObject actualResponse = new JSONObject(response.body().asString());

        compareJsonObject(actualResponse, expectedResponse);
    }


}
