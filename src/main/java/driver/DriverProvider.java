package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;

public class DriverProvider {

    private static DriverProvider instance;
    private static WebDriver driver;

    private static final Logger log = LogManager.getRootLogger();

    private DriverProvider() {
    }

    public static DriverProvider getInstance() {
        if (instance == null) {
            instance = new DriverProvider();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeDriver(){
        driver.quit();
        driver = null;
    }

    public void initializeWebDriver(){
        closeBrowserIfOpened();
        driver = DriverFactory.getBrowser();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    private static void closeBrowserIfOpened(){
        try{
            log.info("Closing the browser...");
            driver.quit();
            log.info("Browser was closed successfully!");
        } catch (NoSuchSessionException | NullPointerException exception){
            log.info("Browser has been already closed!");
        }
    }
}

