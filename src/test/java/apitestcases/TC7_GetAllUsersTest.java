package apitestcases;

import io.restassured.http.ContentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class TC7_GetAllUsersTest extends BaseAPITest {
    protected static Logger logger = LogManager.getLogger(TC7_GetAllUsersTest.class);

    /**
     * Ez a teszt megkeresi az összes profilt és kiírja egy JSON fájlba.
     * GET api/v1/users
     * getAllUsers
     * user controller -> api/v1/users
     */

    @Test
    public void getAllUsersTest() throws IOException {
        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .when()
                .get("/api/v1/users");
        response.prettyPrint();
        response.then()
                .statusCode(200);

        ObjectMapper objectMapper = new ObjectMapper();
        String pathname = "target/allUsersTest_result.json";
        objectMapper.writeValue(new File(pathname), response.getBody().asString());
    }
}


