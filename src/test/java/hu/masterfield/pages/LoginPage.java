package hu.masterfield.pages;

import hu.masterfield.utils.GlobalTestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Bejelentkezési képernyő kezelése")
public class LoginPage extends BasePage {


    protected static Logger logger = LogManager.getLogger(LoginPage.class);

    /*
    Az oldalon található webelementek azonosítása.
    Ezek szükségesek a bejelentkezés megvalósításához és a regisztráció elindításához.
     */

    @FindBy(id = "username")
    private WebElement usernameInput; // bejelentkezés -> felhasználó név

    @FindBy(id = "password")
    private WebElement passwordInput; // bejelentkezés -> jelszó

    @FindBy(id = "submit")
    private WebElement submitButton; // bejelentkezés gomb

    //@Findby (By.LinkText = " Sign Up Here")
    @FindBy(xpath = "//a[text()=' Sign Up Here']")
    private WebElement registrationLink; // regisztrációs link

    @FindBy(xpath = "//span[text()= 'Registration Successful. Please Login.']")
    private WebElement registrationSuccessfulLabel; // sikeres regisztráció visszajelzése

    //@FindBy (xpath = "//p[text()='Az oldal sütiket használ']")
    @FindBy(id = "cc-nb-title")
    private WebElement cookiesUsageMessage; // cookie elem

    //@FindBy (xpath = "//div/text()[contains(.,'Logout completed.')]"
    @FindBy(xpath = "//div[span[text()='Success'] and contains(.,'Logout completed.')]")
    private WebElement logoutCompletedMessage; // sikeres kijelentkezés tájékoztatás


    @FindBy(id = "remember-me")
    private WebElement rememberMeCheckbox; // emlékezz rám választó

    //Konstruktor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Ellenőrzi, hogy megjelentek-e az oldalon a megadott elemek.
     *
     * @return true az oldal betöltődött, megjelentek az elemek és kattinthatóak
     */

    @Step("Login oldal betöltésének ellenőrzése")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(usernameInput) && isLoaded(passwordInput) && isLoaded(submitButton) && isLoaded(registrationLink) && isLoaded(rememberMeCheckbox);
        logger.trace("isLoaded: " + isLoaded);
        return isLoaded;
    }

    /**
     * Ellenőrzi, hogy a sikeres regisztráció után megjelent-e az oldalon a megadott elem.
     *
     * @return true az oldalon megjelent az elvárt szöveg.
     */

    @Step("Regisztráció sikerességének ellenőrzése")
    public boolean registrationIsSuccesful() {
        logger.info("Registration is successful() called");
        boolean isRegistrationSuccessful = isLoaded(registrationSuccessfulLabel);
        logger.trace("Registration is successful: " + isRegistrationSuccessful);

        assertEquals("Digital Bank", driver.getTitle());
        assertTrue(driver.getCurrentUrl().endsWith("/bank/register"));
        assertEquals("Success", registrationSuccessfulLabel.getText());

        return isRegistrationSuccessful;
    }

    /**
     * Bejelentkezést megvalósító függvény
     *
     * @param username Digital Bank felhasználó
     * @param password Digital Bank felhasználó jelszava
     * @return a nyitó oldalt megvalósító HomePage objektum
     */

    @Step("Bejelentkezés")
    public HomePage login(String username, String password) {
        logger.info("login() called with username: " + username + " , password" + password + ".");

        setTextbox(usernameInput, "usernameInput", username);
        setTextbox(passwordInput, "passwordInput", password);

        takesScreenshot();
        logger.trace("submitButton.click() called");
        submitButton.click();

        takesScreenshot();
        return new HomePage(driver);
    }

    /**
     * Regisztráció indítása
     */
    @Step("Regisztráció")
    public void registrationStart() {
        logger.trace("RegistrationLink.click() called");
        registrationLink.click();
    }

    /**
     * @return true abban az esetben, ha sikeres volt a kilépés
     */
    @Step("Logout ellenőrzése")
    public boolean validateLogout() {
        boolean isLogoutSuccess = isLoaded(usernameInput) && isLoaded(passwordInput) && isLoaded(submitButton) && isLoaded(registrationLink) && isLoaded(rememberMeCheckbox) && isLoaded(logoutCompletedMessage);
        logger.info("isLogoutSuccess: " + isLogoutSuccess);
        return isLogoutSuccess;
    }

    /**
     * Cookiek ellenőrzése
     *
     * @return true, ha a "sütiket használ az oldal" szöveget tartalmazó webelement látható az oldalon.
     */

    @Step("Cookiek ellenőrzése")
    public boolean isCookieVisible() {
        boolean isCookieVisible = cookiesUsageMessage.isDisplayed();
        logger.info("isCookieVisible: " + isCookieVisible);
        return isCookieVisible;
    }


    public void setTextbox(WebElement webElement, String webElementName, String text) {
        if (webElement.getText().isEmpty()) {
            //TO DO NOTHING
        } else {
            try {
                usernameInput.clear();
                logger.trace(webElementName + ".sendKeys() called");
            } catch (Exception ex) {
                logger.warn(webElementName + " textbox cannot clear.");
            }
        }
        webElement.sendKeys(text);
    }
}