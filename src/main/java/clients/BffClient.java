package clients;

import enums.UrlPattern;

import static io.restassured.RestAssured.given;

public class BffClient {

    private static final String baseUrl = "http://localhost:8000";

    public static <T> T postRequest(UrlPattern urlPattern, int statusCode, Object body, Class<T> responseType) {
        return given()
                .body(body)
                .when()
                .post(baseUrl + urlPattern.pattern)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .extract().as(responseType);
    }

}
