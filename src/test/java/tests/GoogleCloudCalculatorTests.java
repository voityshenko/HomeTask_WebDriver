package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EmailPage;
import pages.GoogleCloudCalculator;
import pages.GoogleCloudPage;
import tests.driver.DriverProvider;


public class GoogleCloudCalculatorTests extends BaseTest {

    private static final String EXPECTED_VMCLASS = "VM class: regular";
    private static final String EXPECTED_INSTANCE = "Instance type: n1-standard-8";
    private static final String EXPECTED_SSD = "2x375 GiB";
    private static final String EXPECTED_TERM = "Commitment term: 1 Year";

    @Test
    public void calculatePriceForComputeEngine() {
        GoogleCloudCalculator googleCloudCalculator = new GoogleCloudCalculator(DriverProvider.getInstance().getDriver());
        GoogleCloudPage googleCloudPage = new GoogleCloudPage(DriverProvider.getInstance().getDriver());
        googleCloudPage.openCloudCalculator();
        googleCloudCalculator.fillForm();
        googleCloudCalculator.pushAddToEstimate();

        Assert.assertEquals(googleCloudCalculator.getFieldVMClass(), EXPECTED_VMCLASS);
        Assert.assertTrue(googleCloudCalculator.getInstanceType().contains(EXPECTED_INSTANCE));
        Assert.assertTrue(googleCloudCalculator.getLocalSsd().contains(EXPECTED_SSD));
        Assert.assertEquals(googleCloudCalculator.getCommitmentTerm(), EXPECTED_TERM);

    }

    @Test
    public void checkEmailForComputeEngine() {
        GoogleCloudCalculator googleCloudCalculator = new GoogleCloudCalculator(DriverProvider.getInstance().getDriver());
        EmailPage emailPage = new EmailPage(DriverProvider.getInstance().getDriver());
        GoogleCloudPage googleCloudPage = new GoogleCloudPage(DriverProvider.getInstance().getDriver());
        googleCloudPage.openCloudCalculator();
        googleCloudCalculator.fillForm();
        googleCloudCalculator.pushAddToEstimate();
        googleCloudCalculator.emailEstimateButtonClick();
        emailPage.createRandomEmail();
        googleCloudCalculator.pasteEmail(emailPage.copyNewGeneratedEmail());
        emailPage.checkInbox();

        Assert.assertTrue((googleCloudCalculator.getPriceFromCalculator()).contains(emailPage.getPriceFromEmail()));

    }

}
