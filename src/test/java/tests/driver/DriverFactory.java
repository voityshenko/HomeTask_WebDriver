package tests.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import logger.LoggerProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static final Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

    public static WebDriver getBrowser(String browserName) {
        WebDriver driver = null;

        switch (browserName) {
            case "Firefox" -> {
                driver = drivers.get("Firefox");
                if (driver == null) {
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setHeadless(false);
                    driver = new FirefoxDriver(firefoxOptions);
                    drivers.put("Firefox", driver);
                    LoggerProvider.instance().getLogger().log(" Firefox Driver is Created ");
                }
            }
            case "Chrome" -> {
                driver = drivers.get("Chrome");
                if (driver == null) {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setHeadless(false);
                    driver = new ChromeDriver(chromeOptions);
                    drivers.put("Chrome", driver);
                    LoggerProvider.instance().getLogger().log("Chrome Driver is Created ");
                }
            }
        }
        return driver;
    }

    public static void closeAllDriver() {
        for (String key : drivers.keySet()) {
            drivers.get(key).close();
            drivers.get(key).quit();
        }
    }
}

