package hooks;

import java.io.IOException;

import Base.DriverFactory;
import config.Config;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	@Before
    public void initializeDriver() throws IOException {
        
        // 1. Initialize the ThreadLocal driver via Factory
        DriverFactory.init(Config.getProperty("browser"));
        // 2. Use getDriver() directly - DO NOT use 'this.driver'
        DriverFactory.getDriver().get(Config.getProperty("url"));
        DriverFactory.getDriver().manage().window().maximize();
        
    }

    @After
    public void tearDown() {
        // Clean up to prevent memory leaks as per Task 4
        DriverFactory.tearDriver(); 
    }
}