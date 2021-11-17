package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EmailPage;
import tests.driver.DriverProvider;

public class EmailPageTests extends BaseTest {
    private static final String EXPECTED_LANGUAGE = "ru";

    @Test
    public void checkEmailLanguage() {
        EmailPage emailPage = new EmailPage(DriverProvider.getInstance().getDriver());
        emailPage.openEmailPage();
        emailPage.changeLanguage();

        Assert.assertTrue(emailPage.getCurrentUrl().contains(EXPECTED_LANGUAGE));
    }
}
