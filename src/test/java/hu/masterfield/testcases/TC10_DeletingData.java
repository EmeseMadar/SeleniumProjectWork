package hu.masterfield.testcases;

import hu.masterfield.pages.*;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import hu.masterfield.utils.Screenshot;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.operator.AsymmetricKeyUnwrapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TC10_DeletingData extends BaseTest {

    /**
     * TC10 - Accountok sikeres törlésének vizsgálata
     */

    protected static Logger logger = LogManager.getLogger(hu.masterfield.testcases.TC10_DeletingData.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();

    @Test
    @DisplayName("TC10_DeleteData")
    @Description("TC10 - Accountok sikeres törlésének vizsgálata.")
    @Tag("TC10")
    @Tag("Adatforrás")
    @Tag("Törlés")

    public void TC10_DeletingData(TestInfo testInfo) throws InterruptedException, IOException {

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

        //Accountok törlése
        logger.info("deleteData() called.");
        HomePage homePageOne = homePage.deleteData();
        takesScreenshot();

        // Törlés után a View Savings ellenőrzése
        homePage.gotoViewSavingsPage();
        ViewSavingsAccountsPage viewSavingsAccountsPage = new ViewSavingsAccountsPage(driver);
        viewSavingsAccountsPage.isLoadedAfterDeletingData();
        logger.info(testInfo.getDisplayName() + " successfully finished.");
        takesScreenshot();
    }
}
