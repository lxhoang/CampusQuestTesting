package api.endpoints;

import api.utilities.CommonTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TwitterProfileEndPoints {

    public static Response getProfile() {
        String ocIdTokenCookie = CommonTest.getEnvProperties().getString("oc_id_token_cookie");
        Response response =
                given()
                        .cookie(ocIdTokenCookie)
                .when()
                        .get(Routes.twitter_profile_get_url);

        return response;
    }

    public static Response getProfile(String ocIdTokenCookie) {
        Response response =
                given()
                        .cookie(ocIdTokenCookie)
                        .when()
                        .get(Routes.twitter_profile_get_url);

        return response;
    }

}
