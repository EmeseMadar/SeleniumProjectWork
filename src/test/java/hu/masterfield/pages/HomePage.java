package hu.masterfield.pages;

import hu.masterfield.utils.Consts;
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

@Feature("Az oldalon található elemek azonosítása. Ezek szükségesek a HomePage-ről induló műveletekhez.")
public class HomePage extends BasePage {

    protected static Logger logger = LogManager.getLogger(HomePage.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();


    //Checking menu
    @FindBy(id = "checking menu")
    private WebElement checkingMenu;

    //Checking menu ->View Checking
    @FindBy(id = "view-checking-menu-item")
    private WebElement viewCheckingMenuItem;

    //Checking menu -> New Checking
    @FindBy(id = "new-checking-menu-item")
    private WebElement newCheckingMenuItem;

    //Savings Menu
    @FindBy(id = "savings-menu")
    private WebElement savingsMenu;

    //Savings Menu -> View Savings
    @FindBy(id = "view-savings-menu-item")
    private WebElement viewSavingsMenuItem;

    //Savings Menu -> New Savings
    @FindBy(id = "new-savings-menu-item")
    private WebElement newSavingsMenuItem;

    //Welcome Message
    //FindBy (css = "div.page-title > ol > li")
    //FindBy (css = "div[class = 'page-title'] li")
    @FindBy(xpath = "//div[@class='page-title']//li")
    private WebElement labelTitle;

    //User avatar / Image
    @FindBy(css = "img.user-avatar.rounded-circle")
    private WebElement avatarDropdownMenuButton;

    //User avatar -> My profile
    //@FindBy (css = "a.nav-link[href='/bank/user/profile']")
    //@FindBy (css = "a.nav-link[href=\"/bank/user/profile"]")
    @FindBy(xpath = "//a[contains(text(), 'My Profile')]")
    private WebElement avatarDropdownMyProfileLink;

    ///User avatar -> Change password
    //@FindBy (css = "a.nav-link[href='/bank/user/password']")
    @FindBy(xpath = "//a[contains(text(), 'Change Password')]")
    private WebElement avatarDropdownChangePasswordLink;

    ///User avatar -> Create Data
    //@FindBy (css = "a.nav-link[href='/bank/user/create-data']")
    @FindBy(xpath = "//a[contains(text(), 'Create Data')]")
    private WebElement avatarDropdownCreateDataLink;

    ///User avatar -> Delete Data
    //@FindBy (css = "a.nav-link[href='/bank/user/delete-data']")
    @FindBy(xpath = "//a[contains(text(), 'Delete Data')]")
    private WebElement avatarDropdownDeleteDataLink;

    ///User avatar -> Logout
    //@FindBy (css = "a.nav-link[href='/bank/logout']")
    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    private WebElement avatarDropdownLogoutLink;

    //Transactions/Transfers -> Deposit
    @FindBy(id = "deposit-menu-item")
    private WebElement depositLink;

    //Transactions/Transfers -> Withdraw
    @FindBy(id = "withdraw-menu-item")
    private WebElement withdrawLink;

    /**
     * A HomePage-ről elnavigálunk a Create Savings oldalra a menüben.
     *
     * @return a megnyitott CreateSavings oldal objektuma
     */

    @Step("Navigálás a Create Savings oldalra.")
    public CreateSavingsPage gotoNewSavingsPage() {
        logger.info("gotoNewSavingsPage() called.");

        logger.trace("savingsMenu.click()");
        savingsMenu.click();

        logger.trace("newSavingsMenuItem.click()");
        newSavingsMenuItem.click();

        takesScreenshot();

        return new CreateSavingsPage(driver);
    }

    /**
     * A HomePage-ről elnavigálunk a View Savings Oldalra.
     *
     * @return a megnyitott ViewSavings oldal objektuma.
     */
    @Step("Navigálás a View Savings oldalra.")
    public ViewSavingsAccountsPage gotoViewSavingsPage() {
        logger.info("gotoViewSavingsPage() called");

        logger.trace("savingsMenu.click()");
        savingsMenu.click();

        logger.trace("viewSavingsMenuItem.click()");
        viewSavingsMenuItem.click();

        takesScreenshot();

        return new ViewSavingsAccountsPage(driver);
    }

    /**
     * A HomePage-ről elnavigálunk a Deposit oldalra.
     *
     * @return a megnyitott Deposit oldal objektuma.
     */
    @Step("Navigálás a Deposit oldalra.")
    public DepositPage gotoDepositPage() {
        logger.info("gotoDepositPage() called.");

        logger.trace("depositLink.click()");
        depositLink.click();

        takesScreenshot();

        return new DepositPage(driver);
    }

    /**
     * HomePageről elnavigálunk a Savings/Transactions oldalra.
     *
     * @return a megnyitott Savings/Transactions oldal objektuma
     */
    @Step("Navigálás a TransactionsPage-re.")
    public TransactionsPage gotoTransactionsPage() {
        logger.info("gotoTransactionsPage() called.");

        logger.trace("savingMenu.click()");
        savingsMenu.click();

        logger.trace("viewSavingsMenuItem.click()");
        viewSavingsMenuItem.click();

        takesScreenshot();

        return new TransactionsPage(driver);
    }

    /**
     * HomePageről elnavigálunk a MyProfile oldalra.
     *
     * @return a megynitott MyProfil oldal objektuma
     */

    @Step("Navigálás a profil adatokat megjelenítő és módosító oldalra.")
    public MyProfilePage gotoMyProfilePage() {
        logger.info("gotoMyProfilePage() called");

        logger.trace("avatarDropdownMenuButton.click()");
        avatarDropdownMenuButton.click();

        logger.trace("avatarDropdownMyProfileLink.click()");
        avatarDropdownMyProfileLink.click();

        takesScreenshot();

        return new MyProfilePage(driver);
    }

    /**
     * Adatok törlése
     */
    @Step("Adatok törlése.")
    public void deleteData() {
        logger.info("deleteData() called");

        logger.trace("avatarDropdownMenuButton.click()");
        avatarDropdownMenuButton.click();

        logger.trace("avatarDropdownDeleteDataLink.click();");
        avatarDropdownDeleteDataLink.click();

        takesScreenshot();
    }

    /**
     * Ellenőrzési a HomePage betöltődését
     *
     * @return true, ha betöltődött a HommePage
     */

    @Step("HomePage betöltődésének ellenőrzése.")
    public boolean isLoaded() {
        logger.info("HomePage.isLoaded() called");
        boolean isLoaded = isLoaded(savingsMenu) && isLoaded(checkingMenu) && isLoaded(avatarDropdownMenuButton);
        logger.trace("isLoaded" + isLoaded());

        return isLoaded;
    }

    /**
     * Logout megvalósítása
     *
     * @return LoginPage típusú obejktum
     */
    @Step("Logout megvalósítása.")
    public LoginPage logout() {
        logger.info("logout() called");

        logger.trace("avatarDropdownMenuButton.click()");
        avatarDropdownMenuButton.click();

        logger.trace("avatarDropdownLogoutLink.click()");
        avatarDropdownLogoutLink.click();

        takesScreenshot();

        return new LoginPage(driver);
    }

    /**
     * HomePage ellenőrzése.
     */
    @Step("HomePage ellenőrzése.")
    public void validateHomePage() {
        logger.info("validateHomepage called");
        assertEquals("Digital Bank", driver.getTitle());
        assertTrue(driver.getCurrentUrl().endsWith("/bank/home"));
        assertEquals("Welcome " + globalTestData.getProperty(Consts.REG_FIRST_NAME), labelTitle.getText());
    }

    /**
     * HomePage ellenőrzése profit módosítása után.
     */
    @Step("HomePage ellenőrzése profil módosítása után.")
    public void validateHomePageAfterModifyProfile() {
        logger.info("validateHomepageAfterModifyProfile called");
        assertEquals("Digital Bank", driver.getTitle());
        assertTrue(driver.getCurrentUrl().endsWith("/bank/home"));
        assertEquals("Welcome " + globalTestData.getProperty(Consts.MOD_FIRST_NAME), labelTitle.getText());
        takesScreenshot();
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
