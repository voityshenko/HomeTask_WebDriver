package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetailsPastePastebinPage extends AbstractPage {

    private final String pasteNameXPath = "//div[@class='info-top']/h1";

    @FindBy(xpath = pasteNameXPath)
    private WebElement pasteNameLabel;

    @FindBy(xpath = "//textarea[@class='textarea']")
    private WebElement pasteDataTextarea;

    @FindBy(xpath = "//div[@class='highlighted-code']/div/div/a")
    private WebElement pasteSyntaxLabel;

    protected DetailsPastePastebinPage(WebDriver driver) {
        super(driver);
    }

    public String pasteNameLabelText() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(pasteNameXPath)));
        return pasteNameLabel.getText();
    }

    public String pasteDataTextareaText() {
        return pasteDataTextarea.getText();
    }

    public String pasteSyntaxLabelText() {
        if (pasteSyntaxLabel.getText().equals("text")) {
            return "None";
        } else {
            return pasteSyntaxLabel.getText();
        }
    }

}
