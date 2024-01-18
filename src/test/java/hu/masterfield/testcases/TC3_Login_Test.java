package hu.masterfield.testcases;

import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.HomePage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import jdk.jfr.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TC3 - Sikeres bejelentkezés érvényes adatok megadásával
 */
public class TC3_Login_Test extends BaseTest {
    protected static Logger logger = LogManager.getLogger(TC3_Login_Test.class);

    @Test
    @DisplayName("T3 - Login")
    @Description("TC3 - Sikeres bejelentkezés, regisztrált adatokkal")
    @Tag("TC3")
    @Tag("Bejelentkezés")

    public void TC3_LoginTest(TestInfo testInfo) throws IOException, InterruptedException {
        GlobalTestData globalTestData = new GlobalTestData();
        Thread.sleep(5000);
        logger.info(testInfo + " started.");

        GDPRBannerPage gdprBannerPage = new GDPRBannerPage(driver);
        gdprBannerPage.acceptCookies();

        logger.info("Login page will be opened...");
        /*login*/
        String emailAddress = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        String password = globalTestData.getProperty(Consts.REG_PASSWORD);

        logger.info("Login");
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoaded());

        HomePage homePage = loginPage.login(emailAddress, password);
        assertTrue(homePage.isLoaded());
        homePage.validateHomePage();
    }
}
