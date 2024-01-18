package hu.masterfield.testcases;

import dataTypes.RegistrationData;
import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.pages.RegistrationFirstPage;
import hu.masterfield.pages.RegistrationSecondPage;
import hu.masterfield.utils.Screenshot;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * TC2 - Sikeres regisztráció érvényes adatok megadásával
 */
public class TC2_Registration_Test extends BaseTest {
    private static Logger logger = LogManager.getLogger(TC2_Registration_Test.class);

    @Test
    @DisplayName("TC2_Registration") //JUnit-hoz
    @Description("TC2-Sikeres regisztráció tesztelése érvényes adatokkal") //Allure riporthoz
    @Tag("TC2") //Allure reportban is megjelenik
    @Tag("Regisztráció")

    public void TC2_Registration_Test(TestInfo testInfo) throws IOException, InterruptedException {
        Thread.sleep(5000);
        logger.info(testInfo + " started");

        GDPRBannerPage gdprBannerPage = new GDPRBannerPage(driver);

        //a süti elfogadására szolgáló ablak megjelenésének ellenőrzése
        assertTrue(gdprBannerPage.isCookieMessageVisible());
        Screenshot.takesScreenshot(driver);
        gdprBannerPage.acceptCookies();
        Screenshot.takesScreenshot(driver);
        logger.info("Login page will be opened...");

        LoginPage loginPageOne = new LoginPage(driver);
        assertTrue(loginPageOne.isLoaded());
        loginPageOne.registrationStart();

        RegistrationData registrationData = new RegistrationData();
        logger.info(registrationData);

        logger.info("Registration First Page will be opened... ");
        RegistrationFirstPage registrationFirstPage = new RegistrationFirstPage(driver);
        assertTrue(registrationFirstPage.isLoaded());
        Screenshot.takesScreenshot(driver);
        RegistrationSecondPage registrationSecondPage = registrationFirstPage.registrationFirstPage();


        logger.info("Registration Second Page will be opened... ");
        assertTrue(registrationSecondPage.isLoaded());
        Screenshot.takesScreenshot(driver);
        LoginPage loginPageTwo = registrationSecondPage.registrationSecondPage();

        //Ellenőrzi, hogy a regisztráció sikeres volt-e, erről megjelent-e a szöveg
        logger.info("Is registration successful?");
        assertTrue(loginPageTwo.registrationIsSuccesful());
        Screenshot.takesScreenshot(driver);

        if (loginPageTwo.registrationIsSuccesful()) {
            logger.info("TEST PASSED");
            // TEST PASSED
        } else {
            fail("Registration failed.");
        }
    }
}
