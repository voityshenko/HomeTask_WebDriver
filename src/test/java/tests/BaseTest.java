package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import tests.driver.DriverProvider;

public class BaseTest {

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        DriverProvider.getInstance().getDriver().manage().deleteAllCookies();
        DriverProvider.getInstance().getDriver().quit();
    }

    @AfterTest
    public void tearDown() {
        if (DriverProvider.getInstance().getDriver() != null) {
            DriverProvider.getInstance().getDriver().quit();
        }
    }
}
