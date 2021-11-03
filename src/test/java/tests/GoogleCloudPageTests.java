package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EmailPage;
import pages.GoogleCloudPage;


public class GoogleCloudPageTests extends BaseTest {

    private static final String NUMBER_OF_INSTANCES_VALUE = "4";
    private static final String EXPECTEDVMCLASS = "VM class: regular";
    private static final String EXPECTEDINSTANCE = "Instance type: n1-standard-8";
    private static final String EXPECTEDREGION = "Region: Frankfurt";
    private static final String EXPECTEDSSD = "2x375 GiB";
    private static final String EXPECTEDTERM = "Commitment term: 1 Year";
    public static WebDriver emailDriver;

    @Test
    public void calculatePriceForComputeEngine() {
        GoogleCloudPage googleCloudPage = new GoogleCloudPage(driver);
        googleCloudPage.openCloudPage();
        googleCloudPage.clickSearchButton();
        googleCloudPage.sendKeysToSearchForm();
        googleCloudPage.clickCalculatorPage();
        googleCloudPage.fillNumberOfInstances(NUMBER_OF_INSTANCES_VALUE);
        googleCloudPage.selectSeriesOfMachine();
        googleCloudPage.selectMachineType();
        googleCloudPage.clickAddGpusCheckBox();
        googleCloudPage.selectNumberOfGpus();
        googleCloudPage.selectGpuType();
        googleCloudPage.selectLocalSsd();
        //    googleCloudPage.selectDataCenterLocation();
        googleCloudPage.selectCommittedUsage();
        googleCloudPage.pushAddToEstimate();

        Assert.assertEquals(googleCloudPage.getFieldVMClass(), EXPECTEDVMCLASS);
        Assert.assertTrue(googleCloudPage.getInstanceType().contains(EXPECTEDINSTANCE));
        //      Assert.assertEquals(googleCloudPage.getRegion(), EXPECTEDREGION);
        Assert.assertTrue(googleCloudPage.getLocalSsd().contains(EXPECTEDSSD));
        Assert.assertEquals(googleCloudPage.getCommitmentTerm(), EXPECTEDTERM);

    }

    @Test
    public void checkEmailForComputeEngine() {
        GoogleCloudPage googleCloudPage = new GoogleCloudPage(driver);
        EmailPage emailPage = new EmailPage(driver);
        googleCloudPage.openCloudPage();
        googleCloudPage.clickSearchButton();
        googleCloudPage.sendKeysToSearchForm();
        googleCloudPage.clickCalculatorPage();
        googleCloudPage.fillNumberOfInstances(NUMBER_OF_INSTANCES_VALUE);
        googleCloudPage.selectSeriesOfMachine();
        googleCloudPage.selectMachineType();
        googleCloudPage.clickAddGpusCheckBox();
        googleCloudPage.selectNumberOfGpus();
        googleCloudPage.selectGpuType();
        googleCloudPage.selectLocalSsd();
        //    googleCloudPage.selectDataCenterLocation();
        googleCloudPage.selectCommittedUsage();
        googleCloudPage.pushAddToEstimate();
        googleCloudPage.emailEstimateButtonClick();

        emailPage.openPage();
        emailPage.createRandomEmail();
        googleCloudPage.pasteEmail(emailPage.copyNewGeneratedEmail());
        emailPage.checkInbox();

        Assert.assertTrue((googleCloudPage.getPriceFromCalculator()).contains(emailPage.getPriceFromEmail()));

    }
}
