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
        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .queryParam("username", emailAddress)
                .when()
                .get("/api/v1/user/find");
        response.prettyPrint();
        response.then()
                .statusCode(200)
                .body("userProfile.emailAddress", equalTo(emailAddress));
        logger.info("End /api/v1/user/find method.");

        /* Ellenőrizzük, hogy a válaszban helyesek-e a regisztrációs adatok.*/
        assertEquals(registrationData.getFirstName(), response.path("userProfile.firstName"));
        assertEquals(registrationData.getLastName(), response.path("userProfile.lastName"));
        assertEquals(registrationData.getEmailAddress(), response.path("userProfile.emailAddress"));
        assertEquals(registrationData.getSocialSecurityNumber(), response.path("userProfile.ssn"));
        assertEquals(registrationData.getAddress(), response.path("userProfile.address"));
        assertEquals(registrationData.getCountry(), response.path("userProfile.country"));
        assertEquals(registrationData.getDateOfBirth(), response.path("userProfile.dob"));
        assertEquals(registrationData.getGender(), response.path("userProfile.gender"));
        assertEquals(registrationData.getTitle(), response.path("userProfile.title"));
        assertEquals(registrationData.getLocality(), response.path("userProfile.locality"));
        assertEquals(registrationData.getPostalCode(), response.path("userProfile.postalCode"));
        assertEquals(registrationData.getHomePhone(), response.path("userProfile.homePhone"));
        assertEquals(registrationData.getMobilePhone(), response.path("userProfile.mobilePhone"));
        assertEquals(registrationData.getWorkPhone(), response.path("userProfile.workPhone"));


        // Az id érték kinyerése és tárolása egy változóban
        int userId = response.path("id");

        // A kinyert id érték ellenőrzése vagy további felhasználása
        System.out.println("User ID: " + userId);
    }

    /**
     * Ez a teszt törli a megkeresett profilt.
     * DELETE api/v1/user/{id}
     * deleteUser
     * user controller -> api/v1/user/{id}
     */

    @Test
    public void deleteProfile() {
        logger.info("Start delete/api/v1/user/{id}.");
        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .queryParam("id", userId)
                .when()
                .delete("/api/v1/user/");
        response.prettyPrint();
        response.then()
                .statusCode(405);
        logger.info("End delete/api/v1/user/{id} method.");
    }
}
