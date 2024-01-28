package hu.masterfield.pages;

import dataTypes.ModifyProfileData;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * A regisztrációs adatok módosítása a profilon.
 */
@Feature("Regisztrációs adatok módosítása")
public class MyProfilePage extends BasePage {

    //Az oldalon található webelementek azonosítása, amelyekre szükségünk van.

    protected static Logger logger = LogManager.getLogger(MyProfilePage.class);

    //megszólítás megadása
    @FindBy(id = "title")
    private WebElement titleSelect;

    //keresztnév megadása
    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    //vezetéknév megadása
    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    //Otthoni telefonszám
    @FindBy(id = "homePhone")
    private WebElement homePhoneInput;

    //Mobil telefonszám
    @FindBy(id = "mobilePhone")
    private WebElement mobilePhoneInput;

    //Munkahelyi telefonszám
    @FindBy(id = "workPhone")
    private WebElement workPhoneInput;

    //Lakcím megadása
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

    //submit gomb
    @FindBy(xpath = "//*[@id=\"right-panel\"]/div[2]/div/div/div/div/form/div[2]/button[1]")
    private WebElement submitButton;

    //konstruktor
    public MyProfilePage(WebDriver driver) {
        super(driver);
    }


    /**
     * Ellenőrzi, hogy megjelentek-e az oldalon a megadott elemek.
     *
     * @return true az oldal betöltődött, megjelentek az elemek és kattinthatóak.
     */

    @Step("A regisztrációs form második oldalának betöltődésének ellenőrzése.")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(titleSelect) && isLoaded(firstNameInput) && isLoaded(lastNameInput) && isLoaded(homePhoneInput) && isLoaded(mobilePhoneInput) && isLoaded(workPhoneInput) && isLoaded(addressInput) && isLoaded(localityInput) && isLoaded(regionInput) && isLoaded(postalCodeInput) && isLoaded(countryInput) && isLoaded(submitButton);
        logger.trace("isLoaded: " + isLoaded);
        return isLoaded;
    }

    /**
     * Péládnyosítjuk a RegistrationData osztályt, hogy az oldalon található input mezőket a GlobalTestData.properties fájlban megadott adatokkal.
     * Így a regisztráció 2. oldalának kitöltésekor nem kell felsorolni a sok bemenő paramétert.
     */
    ModifyProfileData modifyProfileData = new ModifyProfileData();

    /**
     * A regisztrációs adatok módosítását valósítjuk meg.
     */
    @Step("A regisztrációs adatok módosítása, megadott adatokkal.")
    public MyProfilePage modifyProfile() {
        logger.info("modifyProfile() called.");

        logger.trace("titleSelect.select");
        Select selectTitle = new Select(titleSelect);
        selectTitle.selectByVisibleText(modifyProfileData.getTitle());

        setTextbox(firstNameInput, "firstNameInput", modifyProfileData.getFirstName());
        setTextbox(lastNameInput, "lastNameInput", modifyProfileData.getLastName());

        setTextbox(homePhoneInput, "homePhoneInput", modifyProfileData.getHomePhone());
        setTextbox(mobilePhoneInput, "mobilePhoneInput", modifyProfileData.getMobilePhone());
        setTextbox(workPhoneInput, "workPhoneInput", modifyProfileData.getWorkPhone());

        setTextbox(addressInput, "addressInput", modifyProfileData.getAddress());
        setTextbox(localityInput, "localityInput", modifyProfileData.getLocality());
        setTextbox(regionInput, "regionInput", modifyProfileData.getRegion());
        setTextbox(postalCodeInput, "postalCodeInput", modifyProfileData.getPostalCode());
        setTextbox(countryInput, "countryInput", modifyProfileData.getCountry());

        takesScreenshot();

        logger.trace("registerButton.click()");
        submitButton.click();

        return new MyProfilePage(driver);
    }
}





