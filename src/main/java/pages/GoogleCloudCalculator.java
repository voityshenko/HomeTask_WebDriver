package pages;

import model.Form;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GoogleCloudCalculator extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private static final String NUMBER_OF_INSTANCES_VALUE = "4";
    private final By newFirstFrame = By.xpath("//iframe[contains(@name,'goog_')]");
    private final String MY_FRAME = "myFrame";
    @FindBy(xpath = "//div[contains (text(),'VM class: regular')]")
    protected WebElement informationInVmClassIsRegular;
    @FindBy(xpath = "//md-select[@placeholder='Datacenter location']")
    private WebElement dataCenterLocation;

    @FindBy(xpath = "//div[contains (text(),'Instance type: n1-standard-8')]")
    private WebElement informationInInstanceTypeIncludeN1Standard8;

    @FindBy(xpath = "//div[contains (text(),'Region: Frankfurt')]")
    private WebElement regionIsFrankfurt;

    @FindBy(xpath = "//div[contains (text(),' 2x375 GiB')]")
    private WebElement localSsdSpace2x375Gib;

    @FindBy(xpath = "//div[contains (text(),'Commitment term: 1 Year')]")
    private WebElement commitmentTermOneYear;

    @FindBy(xpath = "//div[@class='md-select-menu-container cpc-region-select md-active md-clickable']//md-option[@value='europe-west3']")
    private WebElement dataCenterLocationInFrankfurt;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.cud']")
    private WebElement committedUsage;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[@value='1']")
    private WebElement committedUsageValue;

    @FindBy(xpath = "//button[@aria-label='Add to Estimate']")
    private WebElement addToEstimateButton;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement instancesField;

    @FindBy(xpath = "//md-select[@name='series']/parent::md-input-container")
    private WebElement seriesOfMachine;

    @FindBy(xpath = "//md-option")
    private List<WebElement> series;

    @FindBy(xpath = "//label[text()='Machine type']/parent::md-input-container")
    private WebElement machineType;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")
    private WebElement computeEngine;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
    private WebElement gpusCheckBox;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGpus;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option")
    private List<WebElement> numberOfGpusModel;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuType']")
    private WebElement gpuType;

    @FindBy(xpath = "//md-option[@value='NVIDIA_TESLA_V100']")
    private WebElement gpuTypeModel;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.ssd']")
    private WebElement localSsd;

    @FindBy(xpath = "//md-option[@id='select_option_438']")
    private WebElement localSsdModel;

    @FindBy(xpath = "//button[@id='email_quote']")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//form[@name='emailForm']")
    private WebElement emailForm;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmail;

    @FindBy(xpath = "//span[contains(text(), 'Estimate')]")
    private WebElement emailEstimateForm;

    @FindBy(xpath = "//b[contains(text(),'Total')]")
    private WebElement totalEstimatedCostFromComputeEngine;


    public GoogleCloudCalculator(WebDriver driver) {
        super(driver);
    }

    private void fillNumberOfInstances(String keyForNumberOfInstances) {
        WebElement element = driver.findElement(newFirstFrame);
        driver.switchTo().frame(element);
        driver.switchTo().frame(MY_FRAME);
        logger.debug("Frame is switched");
        instancesField.click();
        instancesField.sendKeys(keyForNumberOfInstances);
    }

    private void selectSeriesOfMachine(String value) {
        seriesOfMachine.click();
        Actions actions = new Actions(driver);
        WebElement machineModel = series.stream()
                .filter(i -> i.getAttribute("value").equals(value))
                .findFirst().get();
        waitVisibilityOfElement(20, machineModel);
        actions.moveToElement(machineModel);
        logger.debug("Move to element");
        machineModel.click();
    }

    private void selectMachineType() {
        machineType.click();
        waitVisibilityOfElement(20, computeEngine);
        computeEngine.click();
    }

    private void clickAddGpusCheckBox() {
        waitClickableOfElement(10, gpusCheckBox);
        gpusCheckBox.click();
    }

    private void selectNumberOfGpus(String value) {
        numberOfGpus.click();
        WebElement numberGpu = numberOfGpusModel.stream()
                .filter(i -> i.getAttribute("value").equals(value))
                .findFirst().get();
        waitVisibilityOfElement(10, numberGpu);
        numberGpu.click();
    }

    private void selectGpuType(String value) {
        gpuType.click();
        WebElement gpuType = series.stream()
                .filter(i -> i.getAttribute("value").equals(value))
                .findFirst().get();
        waitVisibilityOfElement(10, gpuType);
        gpuType.click();
    }

    private void selectLocalSsd() {
        localSsd.click();
        waitClickableOfElement(10, localSsd);
        localSsdModel.click();
    }

    private void selectCommittedUsage() {
        committedUsage.click();
        waitClickableOfElement(10, committedUsageValue);
        committedUsageValue.click();
    }

    public GoogleCloudCalculator pushAddToEstimate() {
        addToEstimateButton.click();
        logger.info("Added to estimate");
        return this;
    }

    public String getFieldVMClass() {
        waitClickableOfElement(20, informationInVmClassIsRegular);
        return informationInVmClassIsRegular.getText();
    }

    public String getInstanceType() {
        waitClickableOfElement(20, informationInInstanceTypeIncludeN1Standard8);
        String instanceType = informationInInstanceTypeIncludeN1Standard8.getText();
        return instanceType.split("C")[0];
    }

    public String getLocalSsd() {
        return localSsdSpace2x375Gib.getText();
    }

    public String getCommitmentTerm() {
        return commitmentTermOneYear.getText();
    }

    public GoogleCloudCalculator emailEstimateButtonClick() {
        emailEstimateButton.click();
        waitVisibilityOfElement(20, emailForm);
        logger.info("Estimated price");
        return this;
    }

    public void pasteEmail(String email) {
        WebElement element = driver.findElement(newFirstFrame);
        driver.switchTo().frame(element);
        driver.switchTo().frame(MY_FRAME);
        waitVisibilityOfElement(10, emailEstimateForm);
        emailField.click();
        emailField.sendKeys(email);
        sendEmail.click();
        logger.info("Email is passed in field");
    }

    public String getPriceFromCalculator() {
        WebElement element = driver.findElement(newFirstFrame);
        driver.switchTo().frame(element);
        driver.switchTo().frame(MY_FRAME);
        logger.info("Price is saved");
        return totalEstimatedCostFromComputeEngine.getText();
    }

    public GoogleCloudCalculator fillForm(Form form) {
        fillNumberOfInstances(NUMBER_OF_INSTANCES_VALUE);
        selectSeriesOfMachine(form.getSeriesOfMachine());
        selectMachineType();
        clickAddGpusCheckBox();
        selectGpuType(form.getGpuType());
        selectNumberOfGpus(form.getNumberOfGpus());
        selectLocalSsd();
        selectCommittedUsage();
        logger.info("Form is filled");
        return this;
    }


}
