package tests.driver;

import org.openqa.selenium.WebDriver;

public class DriverProvider {

    private static DriverProvider instance;
    private final WebDriver driver;

    private DriverProvider() {
        driver = DriverFactory.getBrowser("Chrome");
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

