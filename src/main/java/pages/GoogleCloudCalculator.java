package pages;

import model.Form;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

import static org.awaitility.Awaitility.await;

public class GoogleCloudCalculator extends AbstractPage {

    private static final String NUMBER_OF_INSTANCES_VALUE = "4";
    private static final String MY_FRAME = "myFrame";
    private final Logger logger = LogManager.getRootLogger();
    private final By newFirstFrame = By.xpath("//iframe[contains(@name,'goog_')]");
    @FindBy(xpath = "//div[contains (text(),'VM class: regular')]")
    protected WebElement informationInVmClassIsRegular;
    @FindBy(xpath = "//md-select[@placeholder='Datacenter location']")
    private WebElement dataCenterLocation;

    @FindBy(xpath = "//div[contains (text(),'Instance type: n1-standard-8')]")
    private WebElement instanceValue;

    @FindBy(xpath = "//div[contains (text(),'Region: Frankfurt')]")
    private WebElement regionValue;

    @FindBy(xpath = "//div[contains (text(),'2x375')]")
    private WebElement localSsdValue;

    @FindBy(xpath = "//div[contains (text(),'Commitment term: 1 Year')]")
    private WebElement commitmentTermValue;

    @FindBy(xpath = "//div[@class='md-select-menu-container cpc-region-select md-active md-clickable']//md-option[@value='europe-west3']")
    private WebElement dataCenterLocationValue;

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

    @FindBy(xpath = "//md-option[@value='n1']")
    private WebElement seriesOfMachineModel;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option")
    private List<WebElement> numberOfGpusModel;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuType']")
    private WebElement gpuType;

    @FindBy(xpath = "//md-option[@value='NVIDIA_TESLA_V100']")
    private WebElement gpuTypeModel;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.ssd']")
    private WebElement localSsd;

    @FindBy(xpath = "//md-option[@id='select_option_439']")
    private WebElement localSsdModel;

    @FindBy(xpath = "//button[@id='email_quote']")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//form[@name='emailForm']")
    private WebElement emailForm;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//span[contains(text(), 'Estimate')]")
    private WebElement emailEstimateForm;

    @FindBy(xpath = "//b[contains(text(),'Total')]")
    private WebElement totalEstimatedCostFromComputeEngine;


    public GoogleCloudCalculator(WebDriver driver) {
        super(driver);
    }

    private void fillNumberOfInstances() {
        WebElement element = driver.findElement(newFirstFrame);
        driver.switchTo().frame(element);
        driver.switchTo().frame(MY_FRAME);
        logger.debug("Frame is switched");
        elementHighlighter(instancesField);
        instancesField.click();
        instancesField.sendKeys(GoogleCloudCalculator.NUMBER_OF_INSTANCES_VALUE);
    }

    private void selectSeriesOfMachine(String value) {
        seriesOfMachine.click();
        Actions actions = new Actions(driver);
        WebElement machineModel = series.stream()
                .filter(i -> i.getAttribute("value").equals(value))
                .findFirst().get();
        waitVisibilityOfElement(20, machineModel);
        actions.moveToElement(machineModel);
        elementHighlighter(machineModel);
        logger.debug("Move to element");
        machineModel.click();
    }

    private void selectMachineType() {
        machineType.click();
        waitVisibilityOfElement(20, computeEngine);
        elementHighlighter(computeEngine);
        computeEngine.click();
    }

    private void clickAddGpusCheckBox() {
        waitClickableOfElement(10, gpusCheckBox);
        elementHighlighter(gpusCheckBox);
        gpusCheckBox.click();
    }

    private void selectNumberOfGpus(String value) {
        numberOfGpus.click();
        await().atMost(Duration.ofSeconds(5))
                .with()
                .pollInterval(Duration.ofSeconds(1))
                .until(() -> !numberOfGpusModel.isEmpty());
        WebElement numberGpu = getNumberOfGpusModel(value);
        elementHighlighter(numberGpu);
        numberGpu.click();
        logger.info("Number of Gpus is selected");
    }

    private WebElement getNumberOfGpusModel(String value) {
        return numberOfGpusModel.stream()
                .filter(i -> i.getAttribute("value").equals(value))
                .findFirst().orElseThrow(() -> new RuntimeException("Number Of Gpus Model list is empty!"));
    }

    private void selectGpuType(String value) {
        gpuType.click();
        WebElement gpuType = series.stream()
                .filter(i -> i.getAttribute("value").equals(value))
                .findFirst().get();
        waitVisibilityOfElement(10, gpuType);
        elementHighlighter(gpuType);
        gpuType.click();
        logger.info("GpuType is selected");
    }

    private void selectLocalSsd() {
        localSsd.click();
        waitClickableOfElement(10, localSsd);
        elementHighlighter(localSsdModel);
        localSsdModel.click();
        logger.info("Local ssd is selected");
    }

    private void selectCommittedUsage() {
        committedUsage.click();
        waitClickableOfElement(10, committedUsageValue);
        elementHighlighter(committedUsageValue);
        committedUsageValue.click();
        logger.info("Term of usage is selected");
    }

    public GoogleCloudCalculator pushAddToEstimate() {
        elementHighlighter(addToEstimateButton);
        addToEstimateButton.click();
        logger.info("Added to estimate");
        return this;
    }

    public String getFieldVMClass() {
        waitClickableOfElement(20, informationInVmClassIsRegular);
        return informationInVmClassIsRegular.getText();
    }

    public String getInstanceType() {
        waitClickableOfElement(20, instanceValue);
        String instanceType = instanceValue.getText();
        return instanceType.split("C")[0];
    }

    public String getLocalSsd() {
        return localSsdValue.getText();
    }

    public String getCommitmentTerm() {
        return commitmentTermValue.getText();
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
        sendEmailButton.click();
        logger.info("Email is passed in field");
    }

    public String getPriceFromCalculator() {
        WebElement element = driver.findElement(newFirstFrame);
        driver.switchTo().frame(element);
        driver.switchTo().frame(MY_FRAME);
        logger.info("Price is saved");
        return totalEstimatedCostFromComputeEngine.getText();
    }

    public GoogleCloudCalculator fillForm(Form form) throws InterruptedException {
        fillNumberOfInstances();
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
