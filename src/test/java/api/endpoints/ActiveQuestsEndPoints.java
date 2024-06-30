package api.endpoints;

import api.payload.QuestsRecords;
import api.utilities.CommonTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ActiveQuestsEndPoints {

    public static Response getActiveQuests(QuestsRecords questsRecordsPayload) {
        String ocIdTokenCookie = CommonTest.getEnvProperties().getString("oc_id_token_cookie");

        Response response =
                given()
                        .cookie(ocIdTokenCookie)
                        .queryParam("limit",questsRecordsPayload.getLimit())
                        .queryParam("offset",questsRecordsPayload.getOffset())
                .when()
                        .get(Routes.active_quest_url);

        return response;
    }

}
