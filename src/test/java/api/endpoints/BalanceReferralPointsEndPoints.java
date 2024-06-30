package api.endpoints;

import api.utilities.CommonTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BalanceReferralPointsEndPoints {

    public static Response getBalancePoints() {
        String ocIdTokenCookie = CommonTest.getEnvProperties().getString("oc_id_token_cookie");

        Response response =
                given()
                        .cookie(ocIdTokenCookie)
                .when()
                        .get(Routes.api_referral_url);

        return response;
    }

}
