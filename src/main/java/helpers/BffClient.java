package helpers;

import enums.UrlPattern;

import static io.restassured.RestAssured.given;

public class BffClient {

    private static final String baseUrl = "http://localhost:8000";

    public static <T> T postRequest(UrlPattern urlPattern, Object body, Class<T> responseType) {
        return given()
                .body(body)
                .when()
                .post(baseUrl + urlPattern.pattern)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().as(responseType);
    }

}
