package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSUtils {

    private static JavascriptExecutor getJsExecutor(WebDriver driver) {
        return (JavascriptExecutor) driver;
    }

    // Scroll element into view (for lazy-loaded / hidden elements)
    public static void scrollIntoView(WebDriver driver, WebElement element) {
        getJsExecutor(driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // JS click 
    public static void clickUsingJS(WebDriver driver, WebElement element) {
        getJsExecutor(driver)
                .executeScript("arguments[0].click();", element);
    }

    // Get page title via JS 
    public static String getTitle(WebDriver driver) {
        return getJsExecutor(driver)
                .executeScript("return document.title;")
                .toString();
    }
}

