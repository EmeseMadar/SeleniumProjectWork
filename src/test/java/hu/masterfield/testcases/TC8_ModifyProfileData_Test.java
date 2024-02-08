package hu.masterfield.testcases;

import hu.masterfield.pages.*;
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

public class TC8_ModifyProfileData_Test extends BaseTest {
    /**
     * TC8 - Regisztrációs adatok módosítása, megadott adatokkal.
     */

    protected static Logger logger = LogManager.getLogger(TC8_ModifyProfileData_Test.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();

    @Test
    @DisplayName("TC8_ModifyProfile")
    @Description("TC8 - Regisztrációs adatok módosítása, megadott adatokkal.")
    @Tag("TC8")
    @Tag("MyProfile")
    @Tag("Regisztráció")
    @Tag("Módosítás")
    public void TC8_ModifyProfile(TestInfo testInfo) throws InterruptedException, IOException {

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

        //Navigálás a Profil oldalra
        MyProfilePage myProfilePage = homePage.gotoMyProfilePage();
        assertTrue(myProfilePage.isLoaded());
        logger.trace("My Profile Page is Loaded" + myProfilePage.isLoaded());

        //Regisztráiós adatok módosításának megvalósítása
        logger.trace("modifyProfile() called.");
        MyProfilePage myProfilePageModified = myProfilePage.modifyProfile();

        //Oldal frissítése
        driver.navigate().refresh();

        //Profil oldal betöltésének ellenőrzése
        logger.info("isLoadedUpdatePorfile() called.");
        assertTrue(myProfilePage.isLoadedUpdateProfile());


        //HomePage ellenőrzése módosítás után, "Welcome ModifiedName"
        HomePage homePage1 = new HomePage(driver);
        logger.info("validateHomePageAfterModifyProfile() called.");
        homePage1.validateHomePageAfterModifyProfile();

        takesScreenshot();
    }
}

