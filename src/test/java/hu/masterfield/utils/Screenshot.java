package hu.masterfield.utils;

import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A képernyőmentést megvalósító osztály.
 */
public class Screenshot {
    protected static Logger logger = LogManager.getLogger(Screenshot.class);

    @Attachment(value = "Képernyőmentés", type = "image/png")
    public static byte[] takesScreenshot(WebDriver driver) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
        String timestamp = dateFormat.format(new Date());

        byte[] screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        String screenshotsFolder = Consts.SCREENSHOTS_FOLDER;
        if (Files.exists(Path.of(screenshotsFolder))) {
            //TO DO NOTHING
        } else {
            Files.createDirectory(Path.of(screenshotsFolder));
        }
        String filePath = screenshotsFolder + "/DBankDemo_src_" + timestamp + ".png";

        Path directory = Paths.get(screenshotsFolder);

        try(FileOutputStream out = new FileOutputStream(filePath)){
            if (!Files.exists(directory)){
                Files.createDirectory(directory);
            }
            out.write(screen);
            logger.info("taking screenshot to: " + filePath);
            return screen;
        } catch (IOException ex){
            logger.warn("Exception was thrown when took screenshot to: "+filePath);
            throw new RuntimeException(ex);
        }

    }
}
