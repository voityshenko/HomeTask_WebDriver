package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudPage extends AbstractPage {

    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement searchButton;
    @FindBy(xpath = "//input[@class='devsite-search-field devsite-search-query']")
    private WebElement googleSearch;
    @FindBy(xpath = "//b[text()='Google Cloud Platform Pricing Calculator']/parent::a")
    private WebElement switchToCalculator;

    public GoogleCloudPage(WebDriver driver) {
        super(driver);
    }

    private void openCloudPage() {
        driver.get("https://cloud.google.com/");
    }

    private void clickSearchButton() {
        searchButton.click();
    }

    private void sendKeysToSearchForm() {
        googleSearch.sendKeys("Google Cloud Platform Pricing Calculator");
        googleSearch.sendKeys(Keys.ENTER);
    }

    private void clickCalculatorPage() {
        waitVisibilityOfElement(30, switchToCalculator);
        switchToCalculator.click();
    }

    public void openCloudCalculator() {
        openCloudPage();
        clickSearchButton();
        sendKeysToSearchForm();
        clickCalculatorPage();
    }
}
