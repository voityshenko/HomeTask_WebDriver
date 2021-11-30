package driver;

import logger.LoggerProvider;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static final Map<String, WebDriver> drivers = new HashMap<>();
    private static final String CHROME="chrome";
    private static final String FIREFOX="firefox";

    public static WebDriver getBrowser(String browserName) {
        WebDriver driver = null;

        switch (browserName) {
            case FIREFOX : {
                driver = drivers.get(FIREFOX);
                if (driver == null) {
                    driver = new FireFoxDriverManager().getDriver();
                    drivers.put(FIREFOX, driver);
                    LoggerProvider.instance().getLogger().log(" Firefox Driver is Created ");
                    break;
                }
            }
            case CHROME : {
                driver = drivers.get(CHROME);
                if (driver == null) {
                    driver = new ChromeDriverManager().getDriver();
                    drivers.put(CHROME, driver);
                    LoggerProvider.instance().getLogger().log("Chrome Driver is Created ");
                    break;
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

