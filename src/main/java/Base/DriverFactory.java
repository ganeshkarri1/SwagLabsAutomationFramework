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
    	
    WebDriver driver = null;

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver()
                    .avoidBrowserDetection()
                    .browserVersion("140")
                    .setup();

            ChromeOptions options = new ChromeOptions();

            if (Config.getProperty("headless").equalsIgnoreCase("true")) {
                options.addArguments(
                    "--headless=new",
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--disable-gpu",
                    "--window-size=1920,1080"
                );
            }
        }
        else if (browser.equalsIgnoreCase("edge")) {

            WebDriverManager.edgedriver().setup();
            EdgeOptions options1 = new EdgeOptions();
            if(Config.getProperty("headless").equalsIgnoreCase("true")) {
            	options1.addArguments("--headless=new");
            }
            driver = new EdgeDriver(options1);

        } else if (browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options1 = new FirefoxOptions();
            if(Config.getProperty("headless").equalsIgnoreCase("true")) {
            	options1.addArguments("--headless");
            }
            driver = new FirefoxDriver(options1);

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
