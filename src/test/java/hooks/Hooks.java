package hooks;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Base.DriverFactory;
import config.Config;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ExtentManager;
import utils.ScreenshotUtils;

public class Hooks {
	Logger logger=LogManager.getLogger(Hooks.class);

	@Before
    public void initializeDriver() throws IOException {
        
        // 1. Initialize the ThreadLocal driver via Factory
        DriverFactory.init(Config.getProperty("browser"));
        // 2. Use getDriver() directly - DO NOT use 'this.driver'
        DriverFactory.getDriver().get(Config.getProperty("url"));
        DriverFactory.getDriver().manage().window().maximize();
        
    }

	@After
	public void tearDown(io.cucumber.java.Scenario scenario) {
		try {
            if (scenario.isFailed()) {
                logger.error("Scenario failed: " + scenario.getName());

                byte[] screenshot =
                        ((TakesScreenshot) DriverFactory.getDriver())
                                .getScreenshotAs(OutputType.BYTES);

                scenario.attach(
                        screenshot,
                        "image/png",
                        "Failure Screenshot"
                );
            }
        } catch (Exception e) {
            logger.error("Screenshot capture failed", e);
        } finally {
            DriverFactory.tearDriver();
            logger.info("Browser closed");
        }
    }
}  
	


