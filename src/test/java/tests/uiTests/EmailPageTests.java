package tests.uiTests;

import driver.DriverProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EmailPage;
import com.cucumber.testng.hooks.BaseTest;

public class EmailPageTests extends BaseTest {
    private static final String EXPECTED_LANGUAGE = "ru";

    @Test
    public void checkEmailLanguage() {
        EmailPage emailPage = new EmailPage(DriverProvider.getInstance().getDriver())
                .openEmailPage()
                .changeLanguage();

        Assert.assertTrue(emailPage.getCurrentUrl().contains(EXPECTED_LANGUAGE));
    }
}
