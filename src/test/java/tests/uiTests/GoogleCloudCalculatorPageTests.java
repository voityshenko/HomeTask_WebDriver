package tests.uiTests;

import driver.DriverProvider;
import model.Form;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EmailPage;
import pages.GoogleCloudCalculatorPage;
import com.cucumber.testng.hooks.BaseTest;

public class GoogleCloudCalculatorPageTests extends BaseTest {

    public static final String EXPECTED_VMCLASS = "VM class: regular";
    public static final String EXPECTED_INSTANCE = "Instance type: n1-standard-8";
    public static final String EXPECTED_SSD = "2x375 GiB";
    public static final String EXPECTED_TERM = "Commitment term: 1 Year";

    @Test
    public void calculatePriceForComputeEngine() {
        GoogleCloudCalculatorPage googleCloudCalculatorPage = new GoogleCloudCalculatorPage(DriverProvider.getInstance().getDriver())
                .openGoogleCloudCalculatorPage();

        googleCloudCalculatorPage
                .fillForm(new Form("n1", "NVIDIA_TESLA_P100", "2"))
                .pushAddToEstimate();

        Assert.assertEquals(googleCloudCalculatorPage.getFieldVMClass(), EXPECTED_VMCLASS);
        Assert.assertTrue(googleCloudCalculatorPage.getInstanceType().contains(EXPECTED_INSTANCE));
        Assert.assertTrue(googleCloudCalculatorPage.getLocalSsd().contains(EXPECTED_SSD));
        Assert.assertEquals(googleCloudCalculatorPage.getCommitmentTerm(), EXPECTED_TERM);

    }

    @Test
    public void checkEmailForComputeEngine() {
        GoogleCloudCalculatorPage googleCloudCalculatorPage = new GoogleCloudCalculatorPage(DriverProvider.getInstance().getDriver())
                .openGoogleCloudCalculatorPage();

        googleCloudCalculatorPage
                .fillForm(new Form("n1", "NVIDIA_TESLA_P100", "2"))
                .pushAddToEstimate()
                .emailEstimateButtonClick();

        EmailPage emailPage = new EmailPage(DriverProvider.getInstance().getDriver())
                .createRandomEmail();

        googleCloudCalculatorPage.pasteEmail(emailPage.copyNewGeneratedEmail());
        emailPage.checkInbox();

        Assert.assertTrue((googleCloudCalculatorPage.getPriceFromCalculator()).contains(emailPage.getPriceFromEmail()));
    }

}
