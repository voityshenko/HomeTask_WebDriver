package com.cucumber.testng.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty",
                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "html:target/cucumber/CucumberTests.html",
                "json:target/cucumber/CucumberTests.json"},
        monochrome = true,
        tags = "",
        glue = "com.cucumber.testng",
        features = "classpath:com/cucumber/testng/features"
)

public class CucumberTestRunner extends AbstractTestNGCucumberTests {

}
