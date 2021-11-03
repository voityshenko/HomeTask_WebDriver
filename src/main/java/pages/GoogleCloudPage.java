package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudPage extends AbstractPage {

    private final By newFirstFrame = By.xpath("//iframe[contains(@name,'goog_')]");

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

    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@class='devsite-search-field devsite-search-query']")
    private WebElement googleSearch;

    @FindBy(xpath = "//b[text()='Google Cloud Platform Pricing Calculator']/parent::a")
    private WebElement switchToCalculator;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement instancesField;

    @FindBy(xpath = "//md-select[@name='series']/parent::md-input-container")
    private WebElement seriesOfMachine;

    @FindBy(xpath = "//md-option[@value='n1']")
    private WebElement seriesOfMachineModel;

    @FindBy(xpath = "//label[text()='Machine type']/parent::md-input-container")
    private WebElement machineType;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")
    private WebElement computeEngine;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
    private WebElement gpusCheckBox;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGpus;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[@value='1']")
    private WebElement numberOfGpusModel;

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


    public GoogleCloudPage(WebDriver driver) {
        super(driver);
    }

    public void openCloudPage() {
        driver.get("https://cloud.google.com/");
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void sendKeysToSearchForm() {
        googleSearch.sendKeys("Google Cloud Platform Pricing Calculator");
        googleSearch.sendKeys(Keys.ENTER);
    }

    public void clickCalculatorPage() {
        waitVisibilityOfElement(30, switchToCalculator);
        switchToCalculator.click();
    }

    public void fillNumberOfInstances(String keyForNumberOfInstances) {
        WebElement element = driver.findElement(newFirstFrame);
        driver.switchTo().frame(element);
        driver.switchTo().frame("myFrame");
        instancesField.click();
        instancesField.sendKeys(keyForNumberOfInstances);
    }


    public void selectSeriesOfMachine() {
        waitVisibilityOfElement(20, seriesOfMachine);
        seriesOfMachine.click();
        seriesOfMachineModel.click();
    }

    public void selectMachineType() {
        machineType.click();
        waitVisibilityOfElement(20, computeEngine);
        computeEngine.click();
    }


    public void clickAddGpusCheckBox() {
        waitClickableOfElement(10, gpusCheckBox);
        gpusCheckBox.click();
    }

    public void selectNumberOfGpus() {
        numberOfGpus.click();
        waitVisibilityOfElement(10, numberOfGpusModel);
        numberOfGpusModel.click();
    }

    public void selectGpuType() {
        gpuType.click();
        waitVisibilityOfElement(10, gpuTypeModel);
        gpuTypeModel.click();
    }

    public void selectLocalSsd() {
        localSsd.click();
        waitClickableOfElement(10, localSsd);
        localSsdModel.click();
    }

    public void selectDataCenterLocation() {
        dataCenterLocation.click();
        waitClickableOfElement(10, dataCenterLocationInFrankfurt);
        dataCenterLocationInFrankfurt.click();
    }

    public void selectCommittedUsage() {
        committedUsage.click();
        waitClickableOfElement(10, committedUsageValue);
        committedUsageValue.click();
    }

    public void pushAddToEstimate() {
        addToEstimateButton.click();
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

    public String getRegion() {
        return regionIsFrankfurt.getText();
    }

    public String getLocalSsd() {
        return localSsdSpace2x375Gib.getText();
    }

    public String getCommitmentTerm() {
        return commitmentTermOneYear.getText();
    }

    public void emailEstimateButtonClick() {
        emailEstimateButton.click();
        waitVisibilityOfElement(20, emailForm);
    }

    public void pasteEmail(String email) {
        WebElement element = driver.findElement(newFirstFrame);
        driver.switchTo().frame(element);
        driver.switchTo().frame("myFrame");
        waitVisibilityOfElement(10, emailEstimateForm);
        emailField.click();
        emailField.sendKeys(email);
        sendEmail.click();
    }

    public String getPriceFromCalculator() {
        WebElement element = driver.findElement(newFirstFrame);
        driver.switchTo().frame(element);
        driver.switchTo().frame("myFrame");
        return totalEstimatedCostFromComputeEngine.getText();
    }
}