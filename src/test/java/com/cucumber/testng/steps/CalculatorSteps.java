package com.cucumber.testng.steps;

import driver.DriverProvider;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.GoogleCloudCalculatorPage;
import pages.GoogleCloudPage;

import java.util.Map;

public class CalculatorSteps {
    private static String totalEstimatedCost;
    WebDriver driver;
    private GoogleCloudPage googleCloudPage = new GoogleCloudPage(DriverProvider.getInstance().getDriver());
    private GoogleCloudCalculatorPage googleCloudCalculatorPage = new GoogleCloudCalculatorPage(DriverProvider.getInstance().getDriver());

    @When("the user opens Google Cloud Pricing Calculator")
    public void openGoogleCloudHomepage() {
        googleCloudPage.openCloudCalculator();
    }

    @When("the user enters Number of instances {string}")
    public void entersNumberOfInstances(String numberOfInstances) {
        googleCloudCalculatorPage.fillNumberOfInstances(numberOfInstances);
    }

    @When("the user selects Series {string}")
    public void selectSeries(String seriesOption) {
        googleCloudCalculatorPage.selectSeriesOfMachine(seriesOption);
    }

    @When("the user selects Instance type {string}")
    public void selectInstanceType(String instanceTypeOption) {
        googleCloudCalculatorPage.selectMachineType(instanceTypeOption);
    }

    @When("the user selects Add GPUs checkbox")
    public void selectAddGPUsCheckbox() {
        googleCloudCalculatorPage.clickAddGpusCheckBox();
    }

    @When("the user selects Number of GPUs {string}")
    public void selectNumberOfGPUs(String numberOfGPUs) {
        googleCloudCalculatorPage.selectNumberOfGpus(numberOfGPUs);
    }

    @When("the user selects GPU type {string}")
    public void selectGPUType(String gpuTypeOption) {
        googleCloudCalculatorPage.selectGpuType(gpuTypeOption);
    }

    @When("the user selects Local SSD {string}")
    public void selectLocalSSD(String localSsdValue) {
        googleCloudCalculatorPage.selectLocalSsd(localSsdValue);
    }


    @When("the user selects Committed usage {string} year")
    public void selectCommittedUsage(String committedUsageValue) {
        googleCloudCalculatorPage.selectCommittedUsage(committedUsageValue);
    }

    @When("the user clicks Add to estimate button")
    public void clickAddToEstimate() {
        googleCloudCalculatorPage.pushAddToEstimate();
    }

    @Then("correct Total Estimated Cost is displayed")
    public void correctTotalEstimatedCostIsDisplayed(DataTable table) {
        Map data= table.asMap();
        Assert.assertTrue(googleCloudCalculatorPage.getFieldVMClass().contains((CharSequence) data.get("class")));
        Assert.assertTrue(googleCloudCalculatorPage.getInstanceType().contains((CharSequence) data.get("instance")));
        Assert.assertTrue(googleCloudCalculatorPage.getLocalSsd().contains((CharSequence) data.get("localSsd")));
        Assert.assertTrue(googleCloudCalculatorPage.getCommitmentTerm().contains((CharSequence) data.get("term")));
    }

    @And("the user e-mails the estimate to a new disposable address")
    public void theUserEMailsTheEstimateToANewDisposableAddress() {
    }
}
