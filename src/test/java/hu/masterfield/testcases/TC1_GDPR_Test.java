package hu.masterfield.testcases;

import hu.masterfield.pages.BasePage;
import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.utils.Screenshot;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TC1 - GDPR nyilatkozat sikeres elfogadása
 */
public class TC1_GDPR_Test extends BaseTest{
    protected static Logger logger = LogManager.getLogger(TC1_GDPR_Test.class);

    @Test
    @DisplayName("TC1_GDPR_Test")
    @Description("TC1-GDPR nyilatkozat sikeres elfogadása")
    @Tag("TC1")
    @Tag("GDPR")

    public void test_TC1_GDPR(TestInfo testInfo) throws IOException, InterruptedException {
        Thread.sleep(5000);
        logger.info(testInfo + " started");

        GDPRBannerPage gdprBannerPage = new GDPRBannerPage(driver);

        //a süti elfogadására szolgáló ablak megjelenésének ellenőrzése
        assertTrue(gdprBannerPage.isCookieMessageVisible());
        //BasePage.takesScreenshot();
        Screenshot.takesScreenshot(driver);
        gdprBannerPage.acceptCookies();
        Screenshot.takesScreenshot(driver);
        logger.info("Login pager will be opened...");
        logger.info("Login");
        Thread.sleep(5000);
    }
}
