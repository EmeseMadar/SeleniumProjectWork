package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public RegistrationFirstPage(WebDriver driver) {
        super(driver);
    }
}
