package hu.masterfield.pages;

import dataTypes.Saving;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * A Saving típusú accountok megjelenítésésének osztálya.
 * Itt történik az adtforrásból léthezott Saving típusok visszaellenőrzée a létrehozás után.
 */

@Feature("Saving típusú accountok megtekintése és ellenőrzése.")
public class ViewSavingsAccountsPage extends BasePage {

    protected static Logger logger = LogManager.getLogger(ViewSavingsAccountsPage.class);

    //Az oldalon található webelementek azonosítás.

    //accountokhoz tartozó kártyák (amik tartalmaznak minden infót az accountról.
    //@FindBy(xpath = "//div[@id='firstRow']/div/div/form/div[@class='card-body']")
    //@FindBy(xpath = "//div[@id='firstRow']//div[@class='card-body']") -> hosszabb távon ez megfelelőbb
    //@FindBy(css = "div[id='firstRow']div[class='card-body']")
    //@FindBy(css = "div#firstRow > div > div > form > div.card-body")


    @FindBy(css = "div#firstRow div.card-body")
    private List<WebElement> cards;

    //Page title
    @FindBy(xpath = "//h1[text()='View Savings Accounts']")
    private WebElement pageTitle;


    public ViewSavingsAccountsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Megjelenő Savingek ellenőrzése.
     */

    @Step("Megjelenő Savingek ellenőrzése.")
    public void validatePage(Saving expectedSaving) {
        logger.info("validatePage() called.");

        takesScreenshot();

        WebElement account = findAccount(expectedSaving.getAccountName());
        assertNotNull(account, "Account with accountName = " + expectedSaving.getAccountName() + " is not found!");

        WebElement divCardBody = account.findElement(By.xpath("./.."));
        List<WebElement> cardDivs = divCardBody.findElements(By.cssSelector("div > div"));
        Saving actualSaving = getSavingFromCard(cardDivs);
        assertEquals(expectedSaving, actualSaving);
    }

    /**
     * A felületen található összes Saving kártya átalakítása Java objektummá (Saving).
     *
     * @return Saving objektumokat tartalmazó List-tel.
     */

    @Step("A felületen lévő összes Saving átalakítása Java objektummá.")
    public List<Saving> getAllSavings() {
        logger.info("getAllSavings() called.");
        takesScreenshot();
        List<Saving> savingList = new ArrayList<>();
        for (WebElement card : cards) {
            List<WebElement> cardDivs = card.findElements(By.cssSelector("div"));
            savingList.add(getSavingFromCard(cardDivs));
        }
        return savingList;
    }

    private WebElement findAccount(String accountName) {
        for (WebElement card : cards) {
            String labelAccountName = card.findElement(By.xpath("//div[@class='h4 m-0' and @contenteditable='true'])")).getText();
            if (labelAccountName.equals(accountName)) {
                return card;
            }
        }
        return null;
    }

    /**
     * Saving objektum létrehozása a kártyából
     *
     * @param cardDivs kártya div elemei
     * @return Saving típusú objektum
     */

    private Saving getSavingFromCard(List<WebElement> cardDivs) {
        logger.info("getSavingFromCard() called.");

        String openingBalanceText = cardDivs.get(6).getText();
        Saving saving = new Saving(cardDivs.get(1).getText().substring(9),
                cardDivs.get(2).getText().substring(11),
                //cardDivs.get(2).getText().replace("Ownership: ", "")
                cardDivs.get(0).getText(),
                openingBalanceText.substring(10, openingBalanceText.lastIndexOf('.')));
        return saving;
    }

    /**
     * Ellenőrzi, hogy megjelentek-e az oldalon a megadott elemek.
     *
     * @return true az oldal betöltődött, megjelentek az elemek és kattinthatóak
     */
    @Step("ViewSavingsAccountPage betöltésének ellenőrzése.")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(pageTitle) && isCardsLoaded(cards);
        logger.info("Page title: " + pageTitle.getText());
        logger.trace("isLoaded = " +isLoaded);
        return isLoaded;
    }

    public boolean isCardsLoaded(List<WebElement> cards) {
        boolean returnValue = false;
        for (WebElement card : cards) {
            if (isLoaded(card)) {
                returnValue = true;
            } else {
                return false;
            }
        } return returnValue;
    }
}

