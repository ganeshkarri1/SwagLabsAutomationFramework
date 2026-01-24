package Base;

import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    // ThreadLocal ensures each thread has its own isolated driver instance
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * Initializes the driver based on the browser name and stores it in ThreadLocal.
     * @param browser The browser type (chrome, edge, firefox)
     */
    public static void init(String browser) {
        WebDriver driver = null;
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        if (driver != null) {
            tlDriver.set(driver);
        } else {
            throw new RuntimeException("Browser type '" + browser + "' is not supported.");
        }
    }

    /**
     * Retrieves the driver instance for the current thread.
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * Quits the driver and removes it from ThreadLocal to prevent memory leaks.
     */
    public static void tearDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            tlDriver.remove(); // Essential for Task 4 acceptance criteria
        }
    }
}