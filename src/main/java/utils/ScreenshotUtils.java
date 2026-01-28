package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {
        // 1. Generate a timestamp for a unique filename
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());  
        String screenshotsDir =
                System.getProperty("user.dir") + "/screenshots";

        String fileName =
                testName + "_" + timestamp + ".png";

        String absolutePath =
                screenshotsDir + "/" + fileName;
        // 2. Cast driver to TakesScreenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        
        // 3. Capture the image as a file
        File source = ts.getScreenshotAs(OutputType.FILE);
        
        // 4. Set the path: projectRoot/screenshots/testName_timestamp.png
        File destinationFile = new File(absolutePath);
        File screenshotsFolder = new File(screenshotsDir);
        if (!screenshotsFolder.exists()) {
            screenshotsFolder.mkdirs();
        }
        
        try {
            // 5. Save the file to the destination
            FileUtils.copyFile(source, destinationFile);
            System.out.println("Screenshot saved at: " + absolutePath);
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
        
        return "../screenshots/" + fileName;
    }
}
