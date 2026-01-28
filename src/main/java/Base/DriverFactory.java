package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static void init(String browser) {

        WebDriver driver;

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if(Config.getProperty("headless").equalsIgnoreCase("true")) {
            	options.addArguments("--headless=new");
            }
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("edge")) {

            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            if(Config.getProperty("headless").equalsIgnoreCase("true")) {
            	options.addArguments("--headless=new");
            }
            driver = new EdgeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if(Config.getProperty("headless").equalsIgnoreCase("true")) {
            	options.addArguments("--headless");
            }
            driver = new FirefoxDriver(options);

        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        tlDriver.set(driver);
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void tearDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            tlDriver.remove();
        }
    }
}
