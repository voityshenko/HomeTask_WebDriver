package com.cucumber.testng.hooks;

import driver.DriverProvider;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class DriverHooks {

    @Before
    public void setupDriver() {
        DriverProvider.getInstance();
    }

    @After
    public void tearDown() {
        if (DriverProvider.getInstance().getDriver() != null) {
            DriverProvider.getInstance().getDriver().quit();
        }
    }
}
