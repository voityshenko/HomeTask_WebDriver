package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EmailPage;
import pages.GoogleCloudPage;
import tests.driver.DriverProvider;


public class GoogleCloudPageTests extends BaseTest {

    private static final String EXPECTED_VMCLASS = "VM class: regular";
    private static final String EXPECTED_INSTANCE = "Instance type: n1-standard-8";
    private static final String EXPECTED_SSD = "2x375 GiB";
    private static final String EXPECTED_TERM = "Commitment term: 1 Year";

    @Test
    public void calculatePriceForComputeEngine() {
        GoogleCloudPage googleCloudPage = new GoogleCloudPage(DriverProvider.getInstance().getDriver());
        googleCloudPage.openCloudCalculator();
        googleCloudPage.fillForm();
        googleCloudPage.pushAddToEstimate();

        Assert.assertEquals(googleCloudPage.getFieldVMClass(), EXPECTED_VMCLASS);
        Assert.assertTrue(googleCloudPage.getInstanceType().contains(EXPECTED_INSTANCE));
        Assert.assertTrue(googleCloudPage.getLocalSsd().contains(EXPECTED_SSD));
        Assert.assertEquals(googleCloudPage.getCommitmentTerm(), EXPECTED_TERM);

    }

    @Test
    public void checkEmailForComputeEngine() {
        GoogleCloudPage googleCloudPage = new GoogleCloudPage(DriverProvider.getInstance().getDriver());
        EmailPage emailPage = new EmailPage(DriverProvider.getInstance().getDriver());
        googleCloudPage.openCloudCalculator();
        googleCloudPage.fillForm();
        googleCloudPage.pushAddToEstimate();
        googleCloudPage.emailEstimateButtonClick();
        emailPage.createRandomEmail();
        googleCloudPage.pasteEmail(emailPage.copyNewGeneratedEmail());
        emailPage.checkInbox();

        Assert.assertTrue((googleCloudPage.getPriceFromCalculator()).contains(emailPage.getPriceFromEmail()));

    }

}
