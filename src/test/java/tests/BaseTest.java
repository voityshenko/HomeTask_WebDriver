package tests;

import driver.DriverProvider;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import utils.TestListener;

@Listeners({TestListener.class})
public class BaseTest {

    @AfterTest
    public void tearDown() {
        if (DriverProvider.getInstance().getDriver() != null) {
            DriverProvider.getInstance().getDriver().quit();
        }
    }
}
