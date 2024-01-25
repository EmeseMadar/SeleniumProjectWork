package hu.masterfield.pages;

import dataTypes.Saving;
import hu.masterfield.utils.Consts;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Megtakarítás típusú accountok létrehozásáért felelős osztály.
 */

@Feature("Megtakarítás típusú accountok létrehozásáért felelős osztály.")
public class CreateSavingsPage extends BasePage {

    protected static Logger logger = LogManager.getLogger(CreateSavingsPage.class);

    //Az oldalon található webelementek azonosítása. Ezek szükségesek a Savings accountok létrehozásához.

    //Savings radio button
    @FindBy(id = Consts.ACCOUNT_TYPES_SAVINGS)
    private WebElement radioSavings;

    //Money Market radio Button
    @FindBy(id = Consts.ACCOUNT_TYPES_MONEY_MARKET)
    private WebElement radioMoneyMarket;

    // Individual radio button
    @FindBy(id = Consts.OWNERSHIP_TYPES_INDIVIDUAL)
    private WebElement radioIndividual;

    // Joint radio button
    @FindBy(id = Consts.OWNERSHIP_TYPES_JOINT)
    private WebElement radioJoint;

    // Account name input
    @FindBy(id = "name")
    private WebElement textName;

    // Opening balance
    @FindBy(id = "openingBalance")
    private WebElement textOpeningBalance;

    // new saving submit gomb
    @FindBy(id = "newSavingsSubmit")
    private WebElement buttonNewSavingsSubmit;

    public CreateSavingsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Új Saving létrehozása
     *
     * @return ViewSavingsAccountPage, ha sikerült létrehozni a Saving típusú objektumot
     */
    @Step("Új Saving létrehozása.")
    public ViewSavingsAccountsPage createNewSaving(Saving saving) {
        logger.info("createNewSaving() called.");

        if (saving.getAccountTypes().equals(Consts.ACCOUNT_TYPES_SAVINGS)) {
            if (radioSavings.isSelected()) {
                // TO DO NOTHING
            } else {
                radioSavings.click();
            }
        }
        if (saving.getAccountTypes().equals(Consts.ACCOUNT_TYPES_MONEY_MARKET)) {
            radioMoneyMarket.click();
        }
        if (saving.getOwnershipTypes().equals(Consts.OWNERSHIP_TYPES_INDIVIDUAL)) {
            radioIndividual.click();
        }
        if (saving.getOwnershipTypes().equals(Consts.OWNERSHIP_TYPES_JOINT)) {
            radioJoint.click();
        }
        //textName.sendKeys(saving.getAccountName());
        setTextbox(textName, "AccountName", saving.getAccountName());
        setTextbox(textOpeningBalance, "Opening Balance", saving.getOpeningBalance());
        takesScreenshot();
        buttonNewSavingsSubmit.click();

        return new ViewSavingsAccountsPage(driver);
    }
}