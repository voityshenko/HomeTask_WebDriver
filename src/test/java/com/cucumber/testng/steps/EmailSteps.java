package com.cucumber.testng.steps;

import driver.DriverProvider;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.EmailPage;
import pages.GoogleCloudCalculatorPage;
import utils.StringUtils;

public class EmailSteps {
    private static String totalEstimatedCost;
    WebDriver driver;
    private final GoogleCloudCalculatorPage googleCloudCalculatorPage = new GoogleCloudCalculatorPage(DriverProvider.getInstance().getDriver());
    private final EmailPage emailPage = new EmailPage(DriverProvider.getInstance().getDriver());

    @And("the user emails estimate to a new disposable address")
    public void theUserEmailsEstimateToANewDisposableAddress() {
        googleCloudCalculatorPage.emailEstimateButtonClick();
        emailPage.createRandomEmail();
        googleCloudCalculatorPage.pasteEmail(emailPage.copyNewGeneratedEmail());
    }

    @And("the email contains correct Total Estimated Cost")
    public void theEmailContainsCorrectTotalEstimatedCost() {
        Assert.assertEquals((StringUtils.getFloatValue(googleCloudCalculatorPage.getPriceFromCalculator())),
                (StringUtils.getFloatValue(emailPage.getPriceFromEmail())));
    }
}
