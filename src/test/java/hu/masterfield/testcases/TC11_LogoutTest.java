package hu.masterfield.testcases;

import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.HomePage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TC11_LogoutTest extends BaseTest {
    /**
     * TC10 - Sikeres Logout viszgálata
     */

    protected static Logger logger = LogManager.getLogger(hu.masterfield.testcases.TC11_LogoutTest.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();

    @Test
    @DisplayName("TC11_LogoutTest")
    @Description("TC11 - Logout")
    @Tag("TC11")
    @Tag("Logout")
    @Tag("Kijelentkezés")

    public void TC11_LogoutTest(TestInfo testInfo) throws InterruptedException, IOException {

        logger.info(testInfo.getDisplayName() + " started.");

        // Cookiek törlése
        GDPRBannerPage gdprBannerPage = new GDPRBannerPage(driver);
        gdprBannerPage.acceptCookies();

        //Login megvalósítása
        String emailAddress = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        String password = globalTestData.getProperty(Consts.REG_PASSWORD);

        logger.info("Login");
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoaded());

        HomePage homePage = loginPage.login(emailAddress, password);
        assertTrue(homePage.isLoaded());
        homePage.validateHomePage();
        takesScreenshot();

        logger.info("Logout() called.");
        homePage.logout();
        takesScreenshot();
    }
}