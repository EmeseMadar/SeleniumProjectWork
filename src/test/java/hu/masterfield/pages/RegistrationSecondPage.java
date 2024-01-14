package hu.masterfield.pages;

import hu.masterfield.utils.Consts;
import io.qameta.allure.Step;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationSecondPage extends BasePage {

    //Az oldalon található webelementek azonosítása, amelyekre szükségünk van.

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

    //Általános Szerződési feltételek elfogadása, választó
    @FindBy(id = "agree-terms")
    private WebElement agreeTermsCheckbox;

    //submit gomb
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

    @Step("Regisztráció első oldalon lévő adatok törlése.")
    public RegistrationSecondPage clearTextbox() {
        clearElement(addressInput, "Address Input");
        clearElement(localityInput, "Locality Input");
        clearElement(regionInput, "Region Input");
        clearElement(postalCodeInput, "PostalCode Input");
        clearElement(countryInput, "Country Input");
        clearElement(homePhoneInput, "HomePhone Input");
        clearElement(mobilePhoneInput, "Mobile Phone Input");
        clearElement(workPhoneInput, "Work Phone Input");

        return new RegistrationSecondPage(driver);
    }

    private void clearElement(WebElement element, String elementName) {
        try {
            element.clear();
            logger.info(elementName + " cleared successfully.");
        } catch (ElementNotInteractableException ex) {
            logger.warn(elementName + " clearing failed: Element not interactable - " + ex.getMessage());
            // Handle this situation accordingly
        } catch (Exception ex) {
            logger.warn(elementName + " clearing failed: " + ex.getMessage());
            // Handle this situation accordingly
        }
    }

    @Step("Regisztráció első oldalon érvényes adatok megadása.")
    public void setTextbox() {

        addressInput.sendKeys(globalTestData.getProperty(Consts.REG_ADDRESS));
        localityInput.sendKeys(globalTestData.getProperty(Consts.REG_LOCALITY));
        regionInput.sendKeys(globalTestData.getProperty(Consts.REG_REGION));
        postalCodeInput.sendKeys(globalTestData.getProperty(Consts.REG_POSTAL_CODE));
        countryInput.sendKeys(globalTestData.getProperty(Consts.REG_COUNTRY));
        homePhoneInput.sendKeys(globalTestData.getProperty(Consts.REG_HOME_PHONE));
        mobilePhoneInput.sendKeys(globalTestData.getProperty(Consts.REG_MOBILE_PHONE));
        workPhoneInput.sendKeys(globalTestData.getProperty(Consts.REG_WORK_PHONE));

        logger.info("Registration() called with: " + "Adress: " + addressInput + ", Locality: " + localityInput + ", Region: " + regionInput + ", Postal Code: " + postalCodeInput + ", Country: " + countryInput + ", Home Phone: " + homePhoneInput + ", Mobile Phone: " + mobilePhoneInput + "Work Phone: " + workPhoneInput);
        logger.trace("agreeTermsCheckbox.click() called");
        agreeTermsCheckbox.click();
        logger.trace("nextButton.click() called");
        registerButton.click();
        takesScreenshot();
    }
}
