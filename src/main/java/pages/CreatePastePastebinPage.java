package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CreatePastePastebinPage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private static final String EXPIRATION_DROPDOWN = "getElementById(\"select2-postform-format-container\")";
    private static final String SYNTAX_DROPDOWN = "getElementsByClassName(\"content__title -paste\")[0]";

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

    @FindBy(xpath = "//input[@class='select2-search__field']")
    private WebElement syntaxLocator;

    private static final String EXPITATION_LOCATOR = "//li[contains(text(),'%s')]";

    public CreatePastePastebinPage(WebDriver driver) {
        super(driver);
    }

    public CreatePastePastebinPage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public CreatePastePastebinPage inputValuesForPaste(String pasteText, String pasteSyntax,
                                                       String pasteExpiraton, String pasteName) {
        pasteTextInput.sendKeys(pasteText);
        scrollTo(EXPIRATION_DROPDOWN);

        pasteExpiratonDropdown.click();
        scrollTo(EXPIRATION_DROPDOWN);

        driver.findElement(By.xpath(String.format(EXPITATION_LOCATOR, pasteExpiraton ))).click();
        scrollTo(SYNTAX_DROPDOWN);

        pasteSyntaxDropdown.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(syntaxLocator).sendKeys(pasteSyntax,Keys.ENTER).build().perform();
        actions.sendKeys(pasteNameInput,pasteName).build().perform();

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
