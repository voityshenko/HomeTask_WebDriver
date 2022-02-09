package com.cucumber.testng.steps;

import driver.DriverProvider;
import io.cucumber.java.en.And;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.EmailPage;
import pages.GoogleCloudCalculatorPage;
import com.cucumber.testng.hooks.BaseTest;
import utils.StringUtils;

public class EmailSteps extends BaseTest {

    private final GoogleCloudCalculatorPage googleCloudCalculatorPage = new GoogleCloudCalculatorPage(DriverProvider.getInstance().getDriver());
    private final EmailPage emailPage = new EmailPage(DriverProvider.getInstance().getDriver());

    @Step("User emails estimate to a new disposable address")
    @And("the user emails estimate to a new disposable address")
    public void theUserEmailsEstimateToANewDisposableAddress() {
        googleCloudCalculatorPage.emailEstimateButtonClick();
        emailPage.createRandomEmail();
        googleCloudCalculatorPage.pasteEmail(emailPage.copyNewGeneratedEmail());
    }

    @Step("The email contains correct Total Estimated Cost")
    @And("the email contains correct Total Estimated Cost")
    public void theEmailContainsCorrectTotalEstimatedCost() {
        Assert.assertEquals((StringUtils.getFloatValue(googleCloudCalculatorPage.getPriceFromCalculator())),
                (StringUtils.getFloatValue(emailPage.getPriceFromEmail())));
    }
}
