package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DetailsPastePastebinPage extends AbstractPage {


    @FindBy(xpath = "//textarea[@class='textarea']")
    private WebElement pasteDataTextarea;

    @FindBy(xpath = "//div[@class='highlighted-code']/div/div/a")
    private WebElement pasteSyntaxLabel;

    @FindBy(xpath = "//div[@class='info-top']/h1")
    private WebElement pasteNameXPath;

    protected DetailsPastePastebinPage(WebDriver driver) {
        super(driver);
    }

    public String pasteNameLabelText() {
        waitVisibilityOfElement(10,pasteNameXPath);
        return pasteNameXPath.getText();
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
