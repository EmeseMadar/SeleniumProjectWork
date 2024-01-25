package apitestcases;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class Proba extends BaseAPITest {

    @Test
    public void TC_Proba() {
        /* RestAssured.given()
                .header(AUTH_HEADER, "Bearer " + authToken)
                .contentType(JSON)
                .when()
                .get("/api/v1/user")
                .then()
                .statusCode(200);
        */

        ValidatableResponse response = given()
                .header(AUTH_HEADER, "Bearer " + authToken)
                .contentType(JSON)
                .when()
                .get("/api/v1/user")
                .then()
                .statusCode(200)
                .body("username", equalTo("admin@demo.io"));

    }
}
