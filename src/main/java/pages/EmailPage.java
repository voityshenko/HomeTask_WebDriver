package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;


public class EmailPage extends AbstractPage {

    public static final String EMAIL_URL = "https://yopmail.com/en/";

    private String googleCloudWindow;
    private String emailWindow;

    @FindBy(xpath = "//a[contains(@title, 'Disposable Email Address')]")
    private WebElement emailGenerator;

    @FindBy(css = "#egen")
    private WebElement newGeneratedEmail;

    @FindBy(xpath = "//button[@onclick='egengo();']")
    private WebElement checkInboxButton;

    @FindBy(xpath = "//h2[contains(text(),'Estimated')]")
    private WebElement totalEstimatedCostFormMessage;

    @FindBy(xpath = "//div[@id='copy_address']")
    private WebElement copyEmail;

    @FindBy(xpath = "//button[@id='refresh']")
    private WebElement refreshEmail;

    public EmailPage(WebDriver driver) {
        super(driver);
    }


    public EmailPage openPage() {
        googleCloudWindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(EMAIL_URL);
        emailWindow = driver.getWindowHandle();

        return this;
    }

    public EmailPage createRandomEmail() {
        emailGenerator.click();
        return this;
    }

    public String copyNewGeneratedEmail() {
        String email = newGeneratedEmail.getText();
        checkInboxButton.click();
        driver.switchTo().window(googleCloudWindow);
        return email;
    }


    public EmailPage checkInbox() {
        driver.switchTo().window(emailWindow);
        driver.navigate().refresh();
        refreshEmail.click();
        driver.switchTo().window(googleCloudWindow);
        return this;
    }

    public String getPriceFromEmail() {
        driver.switchTo().window(emailWindow);
        refreshEmail.click();
        waitForPageLoadComplete(10);
        return totalEstimatedCostFormMessage.getText();
    }

}