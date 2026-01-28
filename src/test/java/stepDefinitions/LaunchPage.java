package stepDefinitions;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import Base.DriverFactory;
import config.Config;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class LaunchPage  {
	Logger logger=LogManager.getLogger(LaunchPage.class);
	
    @Given("user launches the application")
    public void user_launches_the_application() {
    	logger.info("driver is initializing");
        logger.info("Step: User is verifying application launch");
    }

    @Then("page title should contain {string}")
    public void page_title_should_contain(String expectedTitle) {
    	WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(Integer.valueOf(Config.getProperty("timeout"))));
        // This waits until the title actually contains the expected text
        wait.until(ExpectedConditions.titleContains(expectedTitle));
        // Use the Factory to get the driver for the current thread
        String actualTitle = DriverFactory.getDriver().getTitle();
        logger.info("Actual Title: " + actualTitle);
        Assert.assertTrue(actualTitle.contains(expectedTitle), "Title mismatch!");
        logger.info("driver is closing");
    }
}
