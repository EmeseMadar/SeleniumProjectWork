package hu.masterfield.testcases;

import hu.masterfield.pages.*;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import hu.masterfield.utils.Screenshot;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TC5_ModifyProfile_Test extends BaseTest {
    /**
     * TC5 - Regisztrációs adatok módosítása, megadott adatokkal.
     */

    protected static Logger logger = LogManager.getLogger(hu.masterfield.testcases.TC5_ModifyProfile_Test.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();

    @Test
    @DisplayName("TC5_ModifyProfile")
    @Description("TC5 - Regisztrációs adatok módosítása, megadott adatokkal.")
    @Tag("TC5")
    @Tag("MyProfile")
    @Tag("Regisztráció")
    @Tag("Módosítás")
    public void TC5_ModifyProfile(TestInfo testInfo) throws InterruptedException {

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

        //Regisztráiós adatok módosításának megvalósítása
        MyProfilePage myProfilePage = homePage.gotoMyProfilePage();

        MyProfilePage myProfilePageModified = myProfilePage.modifyProfile();
        driver.navigate().refresh();
        Thread.sleep(2000);
        HomePage homePage1 = new HomePage(driver);
        homePage1.validateHomePageAfterModifyProfile();

    }
}

