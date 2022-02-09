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

public class GoogleCloudCalculatorPage extends AbstractPage {

    private static final String NUMBER_OF_INSTANCES_VALUE = "4";
    private static final String MY_FRAME = "myFrame";
    private final Logger logger = LogManager.getRootLogger();
    private final By newFirstFrame = By.xpath("//iframe[contains(@name,'goog_')]");

    @FindBy(xpath = "//div[contains (text(),'VM class: regular')]")
    protected WebElement informationInVmClassIsRegular;
    @FindBy(xpath = "//md-select[@placeholder='Datacenter location']")
    private WebElement dataCenterLocation;

    @FindBy(xpath = "//div[contains (text(),'Instance type')]")
    private WebElement instanceValue;

    @FindBy(xpath = "//div[contains (text(),'Region: Frankfurt')]")
    private WebElement regionValue;

    @FindBy(xpath = "//div[contains (text(),'Local SSD')]")
    private WebElement localSsdValue;

    @FindBy(xpath = "//div[contains (text(),'Commitment term')]")
    private WebElement commitmentTermValue;

    @FindBy(xpath = "//div[@class='md-select-menu-container cpc-region-select md-active md-clickable']//md-option[@value='europe-west3']")
    private WebElement dataCenterLocationValue;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.cud']")
    private WebElement committedUsage;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[@value='1']")
    private WebElement committedUsageValue;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option")
    private List<WebElement> committedUsageList;

    @FindBy(xpath = "//button[@aria-label='Add to Estimate']")
    private WebElement addToEstimateButton;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement instancesField;

    @FindBy(xpath = "//md-select[@name='series']/parent::md-input-container")
    private WebElement seriesOfMachine;

    @FindBy(xpath = "//md-option")
    private List<WebElement> series;

    @FindBy(xpath = "//label[text()='Machine type']/parent::md-input-container")
    private WebElement machineTypeButton;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")
    private WebElement computeEngine;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option")
    private List<WebElement> machineTypeValue;

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

    @FindBy(xpath = "//div[contains(text(), '375')]")
    private List<WebElement> localSsdModels;

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

    public GoogleCloudCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudCalculatorPage openGoogleCloudCalculatorPage(){
        driver.get("https://cloud.google.com/products/calculator");
        return this;
    }

    public void fillNumberOfInstances(String quantity) {
        WebElement element = driver.findElement(newFirstFrame);
        driver.switchTo().frame(element);
        driver.switchTo().frame(MY_FRAME);
        logger.debug("Frame is switched");
        elementHighlighter(instancesField);
        instancesField.click();
        instancesField.sendKeys(quantity);
    }

    public void selectSeriesOfMachine(String value) {
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

    public void selectMachineType(String value) {
        machineTypeButton.click();
        await().atMost(Duration.ofSeconds(5))
                .with()
                .pollInterval(Duration.ofSeconds(1))
                .until(() -> !machineTypeValue.isEmpty());
        WebElement machineTypeValue = getMachineType(value);
        elementHighlighter(machineTypeValue);
        machineTypeValue.click();
    }

    private WebElement getMachineType(String value) {
        return machineTypeValue.stream()
                .filter(i -> i.getAttribute("value").equals(value))
                .findFirst().orElseThrow(() -> new RuntimeException("MachineType xpath is changed!"));
    }

    public void clickAddGpusCheckBox() {
        waitClickableOfElement(10, gpusCheckBox);
        elementHighlighter(gpusCheckBox);
        gpusCheckBox.click();
    }

    public void selectNumberOfGpus(String value) {
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

    public void selectGpuType(String value) {
        gpuType.click();
        WebElement gpuType = series.stream()
                .filter(i -> i.getAttribute("value").equals(value))
                .findFirst().get();
        waitVisibilityOfElement(10, gpuType);
        elementHighlighter(gpuType);
        gpuType.click();
        logger.info("GpuType is selected");
    }

    public void selectLocalSsd(String value) {
        localSsd.click();
        await().atMost(Duration.ofSeconds(5))
                .with()
                .pollInterval(Duration.ofSeconds(1))
                .until(() -> !localSsdModels.isEmpty());
        WebElement localSsdValue = getLocalSsd(value);
        elementHighlighter(localSsdValue);
        localSsdValue.click();
        logger.info("Local ssd is selected");
    }

    private WebElement getLocalSsd(String value) {
        return localSsdModels.stream()
                .filter(i -> i.getText().contains(value))
                .findFirst().orElseThrow(() -> new RuntimeException("Local ssd  list is empty!"));
    }

    public void selectCommittedUsage(String value) {
        committedUsage.click();
        await().atMost(Duration.ofSeconds(5))
                .with()
                .pollInterval(Duration.ofSeconds(1))
                .until(() -> !committedUsageList.isEmpty());
        WebElement committedUsage = getCommittedUsage(value);
        waitClickableOfElement(10, committedUsage);
        elementHighlighter(committedUsage);
        committedUsage.click();
        logger.info("Term of usage is selected");
    }

    private WebElement getCommittedUsage(String value) {
        return committedUsageList.stream()
                .filter(i -> i.getAttribute("value").equals(value))
                .findFirst().orElseThrow(() -> new RuntimeException("Committed Usage list is empty!"));
    }

    public GoogleCloudCalculatorPage pushAddToEstimate() {
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

    public GoogleCloudCalculatorPage emailEstimateButtonClick() {
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
        logger.info("Price is saved");
        return totalEstimatedCostFromComputeEngine.getText();
    }

    public GoogleCloudCalculatorPage fillForm(Form form) {
        fillNumberOfInstances(NUMBER_OF_INSTANCES_VALUE);
        selectSeriesOfMachine(form.getSeriesOfMachine());
        selectMachineType("CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8");
        clickAddGpusCheckBox();
        selectGpuType(form.getGpuType());
        selectNumberOfGpus(form.getNumberOfGpus());
        selectLocalSsd("2x375");
        selectCommittedUsage("1");
        logger.info("Form is filled");
        return this;
    }


}
