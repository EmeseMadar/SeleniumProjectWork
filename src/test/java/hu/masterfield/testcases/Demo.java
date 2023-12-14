package hu.masterfield.testcases;

import org.junit.jupiter.api.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Demo extends BaseTest {

    protected static Logger logger = LogManager.getLogger(Demo.class);
    @Test
    public void testDemo(){
        logger.info("This is a Demo test");
    }
}
