package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


public class EmailPage extends AbstractPage {

    public static final String EMAIL_URL = "https://yopmail.com/en/";
    private final Logger logger = LogManager.getRootLogger();
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

    @FindBy(xpath = "//div[@class='langitem']")
    private WebElement changeLanguageMenu;

    @FindBy(xpath = "//a[@title='Pусский']")
    private WebElement russianLanguage;

    public EmailPage(WebDriver driver) {
        super(driver);
    }

    public void createRandomEmail() {
        googleCloudWindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(EMAIL_URL);
        emailWindow = driver.getWindowHandle();
        emailGenerator.click();
        logger.info("Random email is created");
    }

    public String copyNewGeneratedEmail() {
        String email = newGeneratedEmail.getText();
        checkInboxButton.click();
        driver.switchTo().window(googleCloudWindow);
        logger.info("Email is copied");
        return email;
    }

    public void checkInbox() {
        driver.switchTo().window(emailWindow);
        driver.navigate().refresh();
        refreshEmail.click();
        driver.switchTo().window(googleCloudWindow);
        logger.info("Inbox is checked");
    }

    public String getPriceFromEmail() {
        driver.switchTo().window(emailWindow);
        refreshEmail.click();
        waitForPageLoadComplete(10);
        logger.info("Price from email is saved");
        return totalEstimatedCostFormMessage.getText();
    }

    public void changeLanguage() {
        Actions actions = new Actions(driver);
        WebElement languageMenu = changeLanguageMenu;
        actions.moveToElement(languageMenu).perform();
        if (logger.isDebugEnabled()) {
            logger.debug("Logger: In debug message");
        }
        russianLanguage.click();
        logger.info("Language is changed");
    }

    public void openEmailPage() {
        driver.get(EMAIL_URL);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}