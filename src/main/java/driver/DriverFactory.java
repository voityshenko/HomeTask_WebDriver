package driver;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    private DriverFactory() {
    }

    public static WebDriver getBrowser() {
        WebDriver driver = null;

        switch (System.getProperty("browser")) {
            case FIREFOX: {
                driver = DriverProvider.getInstance().getDriver();
                if (driver == null) {
                    driver = new FireFoxDriverManager().getDriver();
                    break;
                }
            }
            case CHROME: {
                driver = DriverProvider.getInstance().getDriver();
                if (driver == null) {
                    driver = new ChromeDriverManager().getDriver();
                    break;
                }
            }
        }

        return driver;
    }

}

