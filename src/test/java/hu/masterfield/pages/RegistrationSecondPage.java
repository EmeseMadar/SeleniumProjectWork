package hu.masterfield.pages;

import dataTypes.RegistrationData;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Regisztrációs form második oldalának osztálya
 */
@Feature("regisztráció - 2. oldal")
public class RegistrationSecondPage extends BasePage {

    //Az oldalon található webelementek azonosítása, amelyekre szükségünk van.

    protected static Logger logger = LogManager.getLogger(RegistrationSecondPage.class);

    //Lakcím megadása
    //@FindBy(id = "address")
    //@FindBy(css = "#address")
    @FindBy(xpath = "//*[@id='address']")
    private WebElement addressInput;

    //Helység megadása
    @FindBy(id = "locality")
    private WebElement localityInput;

    //Régió megadása
    @FindBy(id = "region")
    private WebElement regionInput;

    //Irányítószám megadása
    @FindBy(id = "postalCode")
    private WebElement postalCodeInput;

    //Ország megadása
    @FindBy(id = "country")
    private WebElement countryInput;

    //Otthoni telefonszám
    @FindBy(id = "homePhone")
    private WebElement homePhoneInput;

    //Mobil telefonszám
    @FindBy(id = "mobilePhone")
    private WebElement mobilePhoneInput;

    //Munkahelyi telefonszám
    @FindBy(id = "workPhone")
    private WebElement workPhoneInput;

    //Általános Szerződési Feltételek elfogadása, választó
    @FindBy(id = "agree-terms")
    private WebElement agreeTermsCheckbox;

    //submit gomb
    //@FindBy(css = "button[type='submit']")
    //@FindBy(xpath = "//button[@type='submit' and text()='Register']")
    @FindBy(xpath = "//form/button")
    private WebElement registerButton;

    //konstruktor
    public RegistrationSecondPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Ellenőrzi, hogy megjelentek-e az oldalon a megadott elemek.
     *
     * @return true az oldal betöltődött, megjelentek az elemek és kattinthatóak.
     */

    @Step("A regisztrációs form második oldalának betöltődésének ellenőrzése.")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(addressInput) && isLoaded(localityInput) && isLoaded(regionInput) && isLoaded(postalCodeInput) && isLoaded(countryInput) && isLoaded(homePhoneInput) && isLoaded(mobilePhoneInput) && isLoaded(workPhoneInput) && isLoaded(agreeTermsCheckbox) && isLoaded(registerButton);
        logger.trace("isLoaded: " + isLoaded);
        return isLoaded;
    }

    @Step("A regisztrációs űrlap második oldalának kitöltése")
    public LoginPage RegistrationSecondPage() {
        logger.trace("agreeTermsCheckbox.click() called");
        agreeTermsCheckbox.click();
        logger.trace("registerButton.click() called");
        registerButton.click();
        return new LoginPage(driver);
    }

    /**
     * Péládnyosítjuk a RegistrationData osztályt, hogy az oldalon található input mezőket a GlobalTestData.properties fájlban megadott adatokkal.
     * Így a regisztráció 2. oldalának kitöltésekor nem kell felsorolni a sok bemenő paramétert.
     */
    RegistrationData registrationData = new RegistrationData();

    /**
     * Regisztráció második oldalát valósítjuk meg.
     */
    @Step("Regisztrációs űrlap második oldalának kitöltése.")
    public LoginPage registrationSecondPage() {
        logger.info("RegistrationSecondPage() called.");
        setTextbox(addressInput, "addressInput", registrationData.getAddress());
        setTextbox(localityInput, "localityInput", registrationData.getLocality());
        setTextbox(regionInput, "regionInput", registrationData.getRegion());
        setTextbox(postalCodeInput, "postalCodeInput", registrationData.getPostalCode());
        setTextbox(countryInput, "countryInput", registrationData.getCountry());
        setTextbox(homePhoneInput, "homePhoneInput", registrationData.getHomePhone());
        setTextbox(mobilePhoneInput, "mobilePhoneInput", registrationData.getMobilePhone());
        setTextbox(workPhoneInput, "workPhoneInput", registrationData.getWorkPhone());

        logger.trace("agreeTermsCheckBox.click()");
        if (agreeTermsCheckbox.isSelected()) {
            //TO DO NOTHING
        } else {
            agreeTermsCheckbox.click();
        }

        takesScreenshot();

        logger.trace("registerButton.click()");
        registerButton.click();

        return new LoginPage(driver);
    }
}