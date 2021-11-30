package driver;

import org.openqa.selenium.WebDriver;

public class DriverProvider {

    private static DriverProvider instance;
    private static WebDriver driver;

    private DriverProvider() {
        driver = DriverFactory.getBrowser("chrome");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    public static DriverProvider getInstance() {
        if (instance == null) {
            instance = new DriverProvider();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
