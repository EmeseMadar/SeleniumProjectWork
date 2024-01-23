package hu.masterfield.testcases;

import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * TC4 - "Savings" típusú számlák sorozatos létrehozása külső adatforrásból.
 */
public class TC4_Datasource_Test extends BaseTest {
    protected Logger logger = LogManager.getLogger(TC4_Datasource_Test.class);

    @Test
    @DisplayName("TC4_Datasource")
    @Description("TC4 - \"Savings\" típusú számlák sorozatos létrehozása külső adatforrásból.")
    @Tag("TC4")
    @Tag("Sorozatos")
    @Tag("Adatbevitel")
    @Tag("Adatforrás")
    public void TC4_Datasource(TestInfo testInfo) {

    }

}
