package com.cucumber.testng.steps;

import driver.DriverProvider;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.GoogleCloudCalculatorPage;
import pages.GoogleCloudPage;

import java.util.Map;

public class CalculatorSteps {
    private final GoogleCloudPage googleCloudPage = new GoogleCloudPage(DriverProvider.getInstance().getDriver());
    private final GoogleCloudCalculatorPage googleCloudCalculatorPage = new GoogleCloudCalculatorPage(DriverProvider.getInstance().getDriver());

    @Step("User opens Google Cloud Pricing Calculator")
    @When("the user opens Google Cloud Pricing Calculator")
    public void openGoogleCloudHomepage() {
        googleCloudPage.openCloudCalculator();
    }

    @Step("User enters Number of instances")
    @When("the user enters Number of instances {string}")
    public void entersNumberOfInstances(String numberOfInstances) {
        googleCloudCalculatorPage.fillNumberOfInstances(numberOfInstances);
    }

    @Step("User selects Series")
    @When("the user selects Series {string}")
    public void selectSeries(String seriesOption) {
        googleCloudCalculatorPage.selectSeriesOfMachine(seriesOption);
    }

    @Step("User selects Instance type")
    @When("the user selects Instance type {string}")
    public void selectInstanceType(String instanceTypeOption) {
        googleCloudCalculatorPage.selectMachineType(instanceTypeOption);
    }

    @Step("User selects Add GPUs checkbox")
    @When("the user selects Add GPUs checkbox")
    public void selectAddGPUsCheckbox() {
        googleCloudCalculatorPage.clickAddGpusCheckBox();
    }

    @Step("User selects Number of GPUs")
    @When("the user selects Number of GPUs {string}")
    public void selectNumberOfGPUs(String numberOfGPUs) {
        googleCloudCalculatorPage.selectNumberOfGpus(numberOfGPUs);
    }

    @Step("User selects GPU type")
    @When("the user selects GPU type {string}")
    public void selectGPUType(String gpuTypeOption) {
        googleCloudCalculatorPage.selectGpuType(gpuTypeOption);
    }

    @Step("User selects Local SSD")
    @When("the user selects Local SSD {string}")
    public void selectLocalSSD(String localSsdValue) {
        googleCloudCalculatorPage.selectLocalSsd(localSsdValue);
    }

    @Step("User selects Committed usage {string} year")
    @When("the user selects Committed usage {string} year")
    public void selectCommittedUsage(String committedUsageValue) {
        googleCloudCalculatorPage.selectCommittedUsage(committedUsageValue);
    }

    @Step("User clicks Add to estimate button")
    @When("the user clicks Add to estimate button")
    public void clickAddToEstimate() {
        googleCloudCalculatorPage.pushAddToEstimate();
    }

    @Step("Correct Total Estimated Cost is displayed")
    @Then("correct Total Estimated Cost is displayed")
    public void correctTotalEstimatedCostIsDisplayed(DataTable table) {
        Map data = table.asMap();
        Assert.assertTrue(googleCloudCalculatorPage.getFieldVMClass().contains((CharSequence) data.get("class")));
        Assert.assertTrue(googleCloudCalculatorPage.getInstanceType().contains((CharSequence) data.get("instance")));
        Assert.assertTrue(googleCloudCalculatorPage.getLocalSsd().contains((CharSequence) data.get("localSsd")));
        Assert.assertTrue(googleCloudCalculatorPage.getCommitmentTerm().contains((CharSequence) data.get("term")));
    }

    @Step("User e-mails the estimate to a new disposable address")
    @And("the user e-mails the estimate to a new disposable address")
    public void theUserEMailsTheEstimateToANewDisposableAddress() {
    }
}
