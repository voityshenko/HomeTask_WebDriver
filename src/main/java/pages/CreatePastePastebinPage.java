package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatePastePastebinPage {

    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private static final String scrollToExpiratonDropdown = "getElementById(\"select2-postform-format-container\")";
    private static final String scrollToSyntaxDropdown = "getElementsByClassName(\"content__title -paste\")[0]";
    private static final String syntaxDropdownLocator = "//input[@class='select2-search__field']";
    private static final String expitationLocatorBegin = "//li[contains(text(),'";
    private static final String expitationLocatorEnd = "')]";
    private final WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement pasteTextInput;

    @FindBy(id = "select2-postform-format-container")
    private WebElement pasteSyntaxDropdown;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement pasteExpiratonDropdown;

    @FindBy(id = "select2-postform-expiration-result-xr2x-10M")
    private WebElement pasteExpiratonDropdown10Minutes;

    @FindBy(id = "postform-name")
    private WebElement pasteNameInput;

    @FindBy(xpath = "//button[contains(text(),'Create New Paste')]")
    private WebElement createNewPasteButton;

    public CreatePastePastebinPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CreatePastePastebinPage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public CreatePastePastebinPage inputValuesForPaste(String pasteText, String pasteSyntax,
                                                       String pasteExpiraton, String pasteName) {
        pasteTextInput.sendKeys(pasteText);
        scrollTo(scrollToExpiratonDropdown);

        pasteExpiratonDropdown.click();
        scrollTo(scrollToExpiratonDropdown);

        driver.findElement(By.xpath(expitationLocatorBegin + pasteExpiraton + expitationLocatorEnd)).click();
        scrollTo(scrollToSyntaxDropdown);

        pasteSyntaxDropdown.click();
        driver.findElement(By.xpath(syntaxDropdownLocator)).sendKeys(pasteSyntax, Keys.ENTER);
        pasteNameInput.sendKeys(pasteName);
        return this;
    }

    public DetailsPastePastebinPage createNewPaste() {
        createNewPasteButton.click();
        return new DetailsPastePastebinPage(driver);
    }

    private void scrollTo(String getElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document." + getElement + ".scrollIntoView();");
    }
}
