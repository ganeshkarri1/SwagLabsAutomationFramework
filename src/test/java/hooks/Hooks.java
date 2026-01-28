package hooks;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

	    if (scenario.isFailed()) {
	        String path = ScreenshotUtils.captureScreenshot(
	                DriverFactory.getDriver(),
	                scenario.getName().replaceAll(" ", "_")
	        );
	        ExtentManager.screenshotPath.set(path);
	        
	    }
	DriverFactory.tearDriver();    
	}
}

