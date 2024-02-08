package hu.masterfield.testcases;

import dataTypes.Saving;
import hu.masterfield.pages.*;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TC9_DataSaveToCSV extends BaseTest {

    /**
     * A létrehozott "Savings" típusú számlák adatainak (account name, type, balance, ownership) lementése
     * egy **dumpSavings.csv** fájlba, a **target** könyvtárban kialakított helyre.
     */


    protected static Logger logger = LogManager.getLogger(hu.masterfield.testcases.TC9_DataSaveToCSV.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();

    @Test
    @DisplayName("TC9_DataSaveToCSV")
    @Description("TC9 - A létrehozott Savings típusú számlák adatainak lementése CSV fájlba.")
    @Tag("TC9")
    @Tag("Mentés")
    public void TC9_DataSaveToCSV(TestInfo testInfo) {

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

        homePage.gotoViewSavingsPage();
        ViewSavingsAccountsPage viewSavingsAccountsPage = new ViewSavingsAccountsPage(driver);
        assertTrue(viewSavingsAccountsPage.isLoaded());


        // Számlaadatok lekérése és mentése
        List<Saving> savingsList = viewSavingsAccountsPage.getAllSavings();
        saveSavingsToCSV(savingsList);
    }

    private void saveSavingsToCSV(List<Saving> savingsList) {

        String filePath = "target/dumpSavings.csv";
        try (FileWriter csvWriter = new FileWriter(filePath)) {
            csvWriter.append("Account Name,Type,Balance,Ownership\n");
            for (Saving saving : savingsList) {
                csvWriter.append(String.join(",", saving.getAccountName(), saving.getAccountTypes(), saving.getOpeningBalance(), saving.getOwnershipTypes()));
                csvWriter.append("\n");
            }
            logger.info("Savings data saved to CSV file: " + filePath);
        } catch (IOException e) {
            logger.error("Error occurred while saving savings data to CSV file: " + e.getMessage());
        }
    }
}
