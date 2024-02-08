package apitestcases;

import dataTypes.RegistrationData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TC5_DeleteProfileTest extends BaseAPITest {

    protected static Logger logger = LogManager.getLogger(TC5_DeleteProfileTest.class);
    private int userId;
    String expectedError = "Not Found";

    /**
     * Ez a teszt megkeresi a profilt a registrationData properties fájlban megadott adatokkal és ellenőrzi.
     * GET api/v1/user/find
     * getUserByUserName
     * user controller -> api/v1/user/find
     */
    RegistrationData registrationData = new RegistrationData();
    String emailAddress = registrationData.getEmailAddress();
    /* A regisztrációs adatoknál megadott email címet felhasználva megkeressük a profilt és kiírjuk a profil adatait.*/

    @Test
    public void getUserByUserName() {
        logger.info("Start /api/v1/user/find method.");
        Response responseOne = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .queryParam("username", emailAddress)
                .when()
                .get("/api/v1/user/find");
        responseOne.prettyPrint();
        responseOne.then()
                .statusCode(200)
                .body("userProfile.emailAddress", equalTo(emailAddress));
        logger.info("End /api/v1/user/find method.");

        /* Ellenőrizzük, hogy a válaszban helyesek-e a regisztrációs adatok.*/
        assertEquals(registrationData.getFirstName(), responseOne.path("userProfile.firstName"));
        assertEquals(registrationData.getLastName(), responseOne.path("userProfile.lastName"));
        assertEquals(registrationData.getEmailAddress(), responseOne.path("userProfile.emailAddress"));
        assertEquals(registrationData.getSocialSecurityNumber(), responseOne.path("userProfile.ssn"));
        assertEquals(registrationData.getAddress(), responseOne.path("userProfile.address"));
        assertEquals(registrationData.getCountry(), responseOne.path("userProfile.country"));
        assertEquals(registrationData.getDateOfBirth(), responseOne.path("userProfile.dob"));
        assertEquals(registrationData.getGender(), responseOne.path("userProfile.gender"));
        assertEquals(registrationData.getTitle(), responseOne.path("userProfile.title"));
        assertEquals(registrationData.getLocality(), responseOne.path("userProfile.locality"));
        assertEquals(registrationData.getPostalCode(), responseOne.path("userProfile.postalCode"));
        assertEquals(registrationData.getHomePhone(), responseOne.path("userProfile.homePhone"));
        assertEquals(registrationData.getMobilePhone(), responseOne.path("userProfile.mobilePhone"));
        assertEquals(registrationData.getWorkPhone(), responseOne.path("userProfile.workPhone"));


        // Az id érték kinyerése és tárolása egy változóban
        userId = responseOne.path("id");

        // A kinyert id érték ellenőrzése vagy további felhasználása
        System.out.println("User ID: " + userId);


        /**
         * Ez a teszt törli a megkeresett profilt.
         * DELETE api/v1/user/{id}
         * deleteUser
         * user controller -> api/v1/user/{id}
         */

        logger.info("Start delete/api/v1/user/{id}.");
        Response responseTwo = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .pathParam("id", userId)
                .when()
                .delete("/api/v1/user/{id}");
        responseTwo.prettyPrint();
        responseTwo.then()
                .statusCode(204);
        logger.info("End delete/api/v1/user/{id} method.");
    }

    @Test
    public void testFindUserByIdAgain() {

        /* GET /api/v1/user/{id} - GET metódussal lekérdezek egy usert id alapján.         */

        logger.info("Starting GET /api/v1/user/{id}");

        Response response3 = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .pathParam("id", userId)
                .when()
                .get("/api/v1/user/{id}");
        response3.prettyPrint();
        response3.then()
                .statusCode(404);

        //Ellenõrzés hogy nem található már ez a user.

        assertEquals(expectedError, response3.path("error"));

        logger.info("Ending GET /api/v1/user/{id}");
    }
}