package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EmailPage;
import pages.GoogleCloudPage;


public class GoogleCloudPageTests extends BaseTest {

    private static final String NUMBER_OF_INSTANCES_VALUE = "4";
    private static final String EXPECTED_VMCLASS = "VM class: regular";
    private static final String EXPECTED_INSTANCE = "Instance type: n1-standard-8";
    private static final String EXPECTEDREGION = "Region: Frankfurt";
    private static final String EXPECTED_SSD = "2x375 GiB";
    private static final String EXPECTED_TERM = "Commitment term: 1 Year";

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

        Assert.assertEquals(googleCloudPage.getFieldVMClass(), EXPECTED_VMCLASS);
        Assert.assertTrue(googleCloudPage.getInstanceType().contains(EXPECTED_INSTANCE));
        //      Assert.assertEquals(googleCloudPage.getRegion(), EXPECTEDREGION);
        Assert.assertTrue(googleCloudPage.getLocalSsd().contains(EXPECTED_SSD));
        Assert.assertEquals(googleCloudPage.getCommitmentTerm(), EXPECTED_TERM);

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
