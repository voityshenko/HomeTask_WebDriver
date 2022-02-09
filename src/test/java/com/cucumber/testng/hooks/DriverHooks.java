package com.cucumber.testng.hooks;

import driver.DriverProvider;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class DriverHooks {

    @Before
    public void setupDriver() {
        DriverProvider.getInstance().initializeWebDriver();
    }

    @After
    public void tearDown() {
        takeScreenshot();
        if (DriverProvider.getInstance().getDriver() != null) {
            DriverProvider.getInstance().closeDriver();
        }
    }

    @Attachment(type = "image/png")
    private byte[] takeScreenshot() {
        return ((TakesScreenshot) DriverProvider.getInstance().getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }
}


