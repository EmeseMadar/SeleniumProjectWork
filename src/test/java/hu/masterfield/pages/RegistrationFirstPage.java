package hu.masterfield.pages;

import dataTypes.RegistrationData;
import hu.masterfield.utils.Consts;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


/**
 * Regisztrációs form első oldalának osztálya
 */
@Feature("regisztráció - 1. oldal")
public class RegistrationFirstPage extends BasePage {

    //Az oldalon található webelementek azonosítása, amelyekre szükségünk van.

    protected static Logger logger = LogManager.getLogger(RegistrationFirstPage.class);


    //megszólítás megadása
    @FindBy(id = "title")
    private WebElement titleSelect;

    //keresztnév megadása
    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    //vezetéknév megadása
    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    //nem megadása -> férfi
    @FindBy(xpath = "//input[@id=\"gender\" and @value=\"M\" and @type=\"radio\"]")
    private WebElement genderMaleRadioButton;

    //nem megadása -> nő
    //@FindBy(css = "input[type='radio'][name='gender'][value='F']")
    @FindBy(xpath = "//input[@id=\"gender\" and @value=\"F\" and @type=\"radio\"]")
    private WebElement genderFemaleRadioButton;

    //születési dátum megadása
    @FindBy(id = "dob")    //@FindBy(xpath = "//input[@id=\"dob\"]")
    private WebElement dateOfBirthInput;

    //társadalom biztosítási szám megadása
    @FindBy(id = "ssn")    //@FindBy(xpath = "//input[@id=\"ssn\"]")
    private WebElement socialSecurityNumberInput;

    //e-mail cím megadása
    @FindBy(id = "emailAddress")    //@FindBy(xpath = "//input[@id=\"emailAddress\"]")
    private WebElement emailAddressInput;

    //jelszó megadása
    @FindBy(id = "password")  //@FindBy(xpath = "//input[@id=\"password\"]")
    private WebElement passwordInput;

    //jelszó megerősítése
    @FindBy(id = "confirmPassword")  //@FindBy(xpath = "//input[@id=\"confirmPassword\"]")
    private WebElement confirmPasswordInput;

    //submit gomb
    //@FindBy (xpath = "//button[@type='submit'] and text()='Next']")
    @FindBy(xpath = "//form/button")
    private WebElement nextPageButton;

    //konstruktor
    public RegistrationFirstPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Ellenőrzi, hogy megjelentek-e az oldalon a megadott elemek.
     *
     * @return true, ha az oldal betöltődött, megjelentek az elvárt elemek és kattinthatóak.
     */

    @Step("A regisztrációs form első oldalának betöltődésének ellenőrzése.")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(titleSelect) && isLoaded(firstNameInput) && isLoaded(lastNameInput) && isLoaded(genderMaleRadioButton) && isLoaded(genderFemaleRadioButton) && isLoaded(dateOfBirthInput) && isLoaded(socialSecurityNumberInput) && isLoaded(emailAddressInput) && isLoaded(passwordInput) && isLoaded(confirmPasswordInput) && isLoaded(nextPageButton);
        logger.trace("isLoaded: " + isLoaded);
        return isLoaded;
    }

    /**
     * Példányosítjuk a RegistrationData osztályt, hogy az oldalon található input mezőket ki tudjuk tölteni a globalTestData.properties fájlból a felolvasott tesztadatokkal.
     */
    RegistrationData registrationData = new RegistrationData();

    /**
     * Regisztráció első oldalát valósítjuk meg.
     */
    @Step("Regisztrációs űrlap első oldalának kitöltése.")
    public RegistrationSecondPage registrationFirstPage() {
        logger.info("RegistrationFirstPage() called.");
        logger.trace("titleSelect.select");
        Select selectTitle = new Select(titleSelect);
        selectTitle.selectByVisibleText(registrationData.getTitle());

        setTextbox(firstNameInput, "firstNameInput", registrationData.getFirstName());
        setTextbox(lastNameInput, "lastNameInput", registrationData.getLastName());

        if (registrationData.getGender().equals("M")) {
            if (genderMaleRadioButton.isSelected()) {
                //TO DO NOTHING
            } else {
                logger.trace("genderMaleRadioButton.click() called.");
                genderMaleRadioButton.click();
            }
        }
        if (registrationData.getGender().equals("F")) {
            if (genderFemaleRadioButton.isSelected()) {
                // TO DO NOTHING
            } else {
                logger.trace("genderFemaleRadioButton.click() called.");
                genderFemaleRadioButton.click();

            }
        }
        setTextbox(dateOfBirthInput, "dateOfBirthInput", registrationData.getDateOfBirth());
        setTextbox(socialSecurityNumberInput, "socialSecurityNumberInput", registrationData.getSocialSecurityNumber());
        setTextbox(emailAddressInput, "emailAddressInput", registrationData.getEmailAddress());
        setTextbox(passwordInput, "passwordInput", registrationData.getPassword());
        setTextbox(confirmPasswordInput, "confirmPasswordInput", registrationData.getConfirmPassword());

        takesScreenshot();

        logger.trace("nextPageButton.click() called.");
        nextPageButton.click();

        return new RegistrationSecondPage(driver);
    }
}
