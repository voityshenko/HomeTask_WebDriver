package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CreatePastePastebinPage;
import pages.DetailsPastePastebinPage;


public class PastebinTest extends BaseTest {


    @DataProvider(name = "valuesPastebinTest")
    public Object[][] valuesForPastebinTest() {
        return new Object[][]{
                {"Hello from WebDriver",
                        "None",
                        "10 Minutes",
                        "helloweb"},
                {"git config --global user.name  \"New Sheriff in Town\"\n" +
                        "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                        "git push origin master --force",
                        "Bash",
                        "10 Minutes",
                        "how to gain dominance among developers"}
        };
    }

    @Test(dataProvider = "valuesPastebinTest")
    public void createPastePastebinTest(String pasteText, String pasteSyntax, String pasteExpiraton, String pasteName) {

        DetailsPastePastebinPage detailsPastePastebinPage = new CreatePastePastebinPage(driver)
                .openPage()
                .inputValuesForPaste(pasteText, pasteSyntax, pasteExpiraton, pasteName)
                .createNewPaste();

        Assert.assertTrue(detailsPastePastebinPage.pasteNameLabelText().contains(pasteName),
                "Name is incorrect");
        Assert.assertTrue(detailsPastePastebinPage.pasteDataTextareaText().contains(pasteText),
                "Text is incorrect");
        Assert.assertTrue(detailsPastePastebinPage.pasteSyntaxLabelText().contains(pasteSyntax),
                "Syntax is incorrect");
    }

}
