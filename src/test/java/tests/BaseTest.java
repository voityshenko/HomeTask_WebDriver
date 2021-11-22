package tests;

import driver.DriverProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import utils.TestListener;

@Listeners({TestListener.class})
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
