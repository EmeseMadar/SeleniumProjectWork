package hu.masterfield.pages;

import hu.masterfield.utils.Consts;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static java.lang.System.getProperty;

/**
 * Regisztrációs form első oldalának osztálya
 */
public class RegistrationFirstPage extends BasePage {

    //Az oldalon található webelementek azonosítása, amelyekre szükségünk van.

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
    @FindBy(xpath = "//form/button")
    private WebElement nextButton;


    //konstruktor
    public RegistrationFirstPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Ellenőrzi, hogy megjelentek-e az oldalon a megadott elemek.
     *
     * @return true az oldal betöltődött, megjelentek az elemek és kattinthatóak.
     */

    @Step("A regisztrációs form első oldalának betöltődésének ellenőrzése")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(titleSelect) && isLoaded(firstNameInput) && isLoaded(lastNameInput) && (isLoaded(genderMaleRadioButton) | isLoaded(genderFemaleRadioButton)) && isLoaded(dateOfBirthInput) && isLoaded(socialSecurityNumberInput) && isLoaded(emailAddressInput) && isLoaded(passwordInput) && isLoaded(confirmPasswordInput) && isLoaded(nextButton);
        logger.trace("isLoaded: " + isLoaded);
        return isLoaded;
    }

    @Step("Regisztráció első oldalon lévő adatok törlése.")
    public RegistrationFirstPage clearTextbox() {
        //clearSelect(titleSelect, "Title Select");
        clearElement(firstNameInput, "First Name Input");
        clearElement(lastNameInput, "Last Name Input");
        clearRadioButton(genderMaleRadioButton, "Gender Male Input");
        clearRadioButton(genderFemaleRadioButton, "Gender Female Input");
        clearElement(dateOfBirthInput, "Date of Birth Input");
        clearElement(socialSecurityNumberInput, "Social Security Number Input");
        clearElement(emailAddressInput, "E-mail Address Input ");
        clearElement(passwordInput, "Password Input");
        clearElement(confirmPasswordInput, "Confirm Password Input");

        return new RegistrationFirstPage(driver);
    }

    private void clearElement(WebElement element, String elementName) {
        try {
            element.clear();
            logger.info(elementName + " cleared successfully.");
        } catch (Exception ex) {
            logger.warn(elementName + " clearing failed: " + ex.getMessage());
        }
    }

  /*  private void clearSelect(WebElement element, String elementName) {
        try {
            Select select = new Select(element);

            if (select.isMultiple()) {
                // Ha a legördülő menü több választási lehetőséget is támogat
                select.deselectAll();
                logger.info(elementName + " cleared successfully.");
            } else {
                // Ha a legördülő menü csak egy választási lehetőséget támogat
                // Itt a "Please select" opciót választjuk ki, hogy töröljük a kijelölést
                select.selectByVisibleText("Please select");
                logger.info(elementName + " cleared successfully.");
            }
        } catch (Exception ex) {
            logger.warn(elementName + " clearing failed: " + ex.getMessage());
        }
    }
*/

    private void clearRadioButton(WebElement radioButton, String elementName) {
        try {
            if (radioButton.isSelected()) {
                radioButton.click(); // Kijelölés eltávolítása
                logger.info(elementName + " cleared successfully.");
            } else {
                logger.info(elementName + " is not selected, nothing to clear.");
            }
        } catch (Exception ex) {
            logger.warn(elementName + " clearing failed: " + ex.getMessage());
        }
    }


    @Step("Regisztráció első oldalon érvényes adatok megadása.")
    public RegistrationSecondPage setTextbox() {

        titleSelect.sendKeys(globalTestData.getProperty(Consts.REG_TITLE));
        firstNameInput.sendKeys(globalTestData.getProperty(Consts.REG_FIRST_NAME));
        lastNameInput.sendKeys(globalTestData.getProperty(Consts.REG_LAST_NAME));

        String gender = globalTestData.getProperty(Consts.REG_GENDER);
        if (gender != null && !gender.isEmpty()) {
            genderMaleRadioButton.click();
        } else if ("F".equalsIgnoreCase(gender)) {
            genderFemaleRadioButton.click();
        }

        dateOfBirthInput.sendKeys(globalTestData.getProperty(Consts.REG_DATE_OF_BIRTH));
        socialSecurityNumberInput.sendKeys(globalTestData.getProperty(Consts.REG_SOCIAL_SECURITY_NUMBER));
        emailAddressInput.sendKeys(globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS));
        passwordInput.sendKeys(globalTestData.getProperty(Consts.REG_PASSWORD));
        confirmPasswordInput.sendKeys(globalTestData.getProperty(Consts.REG_CONFIRM_PASSWORD));

        logger.info("registration() called with: " + titleSelect + firstNameInput + lastNameInput + ", Date of Birth: " + dateOfBirthInput + ", Social Security Number: " + socialSecurityNumberInput + ", Email address: " + emailAddressInput);
        takesScreenshot();
        logger.trace("nextButton.click() called");
        nextButton.click();
        takesScreenshot();
        return new RegistrationSecondPage(driver);
    }
}

//Ide kellene kettő hibaüzenet ellenőrző
//1. Amikor nincs megadva valamilyen mező és
//2. Amikor már a regisztrált felhasználó van. Ekkor törölni/módosítani a felhasználói adatokat. Újra regisztrálni
