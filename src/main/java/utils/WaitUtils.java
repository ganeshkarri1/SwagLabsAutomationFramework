package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.Config;

public class WaitUtils {

    private static final int TIMEOUT =
            Integer.parseInt(Config.getProperty("timeout"));

    private static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    // Wait for element visibility (By)
    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        return getWait(driver)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element visibility (WebElement)
    public static WebElement waitForVisibility(WebDriver driver, WebElement element) {
        return getWait(driver)
                .until(ExpectedConditions.visibilityOf(element));
    }

    // Wait for element to be clickable
    public static WebElement waitForClickable(WebDriver driver, WebElement element) {
        return getWait(driver)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    // Wait for title to contain text
    public static boolean waitForTitleContains(WebDriver driver, String titleText) {
        return getWait(driver)
                .until(ExpectedConditions.titleContains(titleText));
    }

	
		
	
}


