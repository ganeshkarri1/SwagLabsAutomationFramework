package utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtils {

    // Private helper – not exposed outside
    private static Actions getActions(WebDriver driver) {
        return new Actions(driver);
    }

    // ---------------- MOUSE ACTIONS ----------------

    // Click using Actions (useful when normal click fails)
    public static void click(WebDriver driver, WebElement element) {
        getActions(driver)
                .moveToElement(element)
                .click()
                .perform();
    }

    // Hover over an element
    public static void hover(WebDriver driver, WebElement element) {
        getActions(driver)
                .moveToElement(element)
                .perform();
    }

    // ---------------- KEYBOARD ACTIONS ----------------

    // Press any keyboard key (ENTER, TAB, ESC, etc.)
    public static void pressKey(WebDriver driver, Keys key) {
        getActions(driver)
                .sendKeys(key)
                .perform();
    }

    // ---------------- SCROLL ACTIONS ----------------

    // Scroll until element is in view (Selenium 4)
    public static void scrollToElement(WebDriver driver, WebElement element) {
        getActions(driver)
                .scrollToElement(element)
                .perform();
    }

    // Scroll down by mouse wheel (generic scroll)
    public static void scrollDown(WebDriver driver) {
        getActions(driver)
                .scrollByAmount(0, 300)
                .perform();
    }
}

