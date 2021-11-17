package tests.driver;

import logger.LoggerProvider;
import org.openqa.selenium.WebDriver;

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
                    driver = new FireFoxDriverManager().getDriver();
                    drivers.put("Firefox", driver);
                    LoggerProvider.instance().getLogger().log(" Firefox Driver is Created ");
                }
            }
            case "Chrome" -> {
                driver = drivers.get("Chrome");
                if (driver == null) {
                    driver = new ChromeDriverManager().getDriver();
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

