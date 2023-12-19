package hu.masterfield.pages;

import hu.masterfield.utils.GlobalTestData;
import hu.masterfield.utils.Screenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.fail;


/**
 * A Page POM oszályaink innen fogják örököni a osztályban megvalósított metódusokat.
 * Deklarálja a WebDriver-t, a WebDriverWait-et, inicializálja az objektumokat és ezeket
 * nem kell külön megtennünk minden egyes osztályban.
 */
public class BasePage {
    protected static Logger logger = LogManager.getLogger(BasePage.class);
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static GlobalTestData globalTestData = new GlobalTestData();

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        BasePage.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public boolean isLoaded(WebElement webElement) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
        } catch (TimeoutException ex) {
            fail(ex.getMessage());
            return false;
        }
    }

    public boolean isInteractible(WebElement webElement) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(webElement)).isEnabled();
        } catch (TimeoutException ex) {
            fail(ex.getMessage());
            return false;
        }
    }
    public static void takesScreenshot(){
        try{
            Screenshot.takesScreenshot(driver);
        }catch (IOException ex){
            logger.warn("BasePage.takesScreenshot was thrown IOException: " +ex.getMessage() + ", " + ex.getStackTrace());
        }
    }

}
