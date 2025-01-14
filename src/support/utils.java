package support;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Set;
import java.util.logging.Logger;

public class utils {
    private static repositoryparser repParser = null;
    private static configparser configParser = null;
    private static final Logger logger = Logger.getLogger(utils.class.getName());
    public static AppiumDriver driver = null;

    public static repositoryparser getRepositoryParser() throws Exception {
        if (repParser == null) {
            repParser = new repositoryparser("/support/objectrepo.properties");
            logger.info("Successfully initialised Repository parser");
        }
        return repParser;
    }

    public static configparser getConfigParser() throws Exception {
        if (configParser == null) {
            configParser = new configparser("/support/config.properties");
            logger.info("Successfully initialised Config parser");
        }
        return configParser;
    }

    public static WebDriver getBrowserDriver(String browserName) {
        return browserfactory.getBrowser(browserName);
    }

    public static String getBrowserName() throws Exception {
        return getConfigParser().getPropertyValue("BrowserName");
    }

    public static String getApplicationURL() throws Exception {
        return getConfigParser().getPropertyValue("applicationBaseURL");
    }

    public static void takeScreenshot(WebDriver driver, String sTestCaseName) throws Exception {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + getConfigParser().getPropertyValue("saveScreenshotPath")
                    + sTestCaseName + getConfigParser().getPropertyValue("saveScreenshotFileExtension")));
        } catch (Exception e) {
            logger.info("Class utils | Method takeScreenshot | Exception occurred while capturing ScreenShot for testcase :"
                    + sTestCaseName + " Error:  " + e.getMessage());
            throw new Exception();
        }
    }

    // Explicit waits
    public static void waitForVisibilityOfTheElement(WebDriver driver, By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForPresenceOfTheElement(WebDriver driver, By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForClickableOfTheElement(WebDriver driver, By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Fluent wait
    public static void FluentWait(WebDriver driver, By element){
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }

    public static AppiumDriver getAppiumDriver() throws Exception {

        if (driver == null) {
            //Set the Desired Capabilities
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("appium:deviceName", utils.getConfigParser().getPropertyValue("DeviceName"));
            caps.setCapability("appium:automationName", utils.getConfigParser().getPropertyValue("automationName"));
            caps.setCapability("appium:platformName", utils.getConfigParser().getPropertyValue("PlatformName"));
            caps.setCapability("appium:appPackage", utils.getConfigParser().getPropertyValue("AppPackage"));
            caps.setCapability("appium:appActivity", utils.getConfigParser().getPropertyValue("AppActivity"));
            caps.setCapability("appium:autoGrantPermissions", utils.getConfigParser().getPropertyValue("AutoGrantPermissions"));

            driver = new AndroidDriver (new URL(utils.getConfigParser().getPropertyValue("AppiumServerURL")), caps);
            // Switch to WebView
            Set<String> contextNames = ((AndroidDriver) driver).getContextHandles();
            for (String contextName : contextNames) {
                System.out.println(contextName);
                if (contextName.contains("NATIVE_APP")){
                    ((AndroidDriver) driver).context(contextName);
                    logger.info("Setting mobile drive context to WEBVIEW " + contextName);
                }
            }
        }
        return driver;
    }

    // Generic method to generate random text
    public static String getRandomNumber(String text) {
        String generatedText = text + System.currentTimeMillis();
        System.out.println("Auto Generated text is" + " " + generatedText);
        return generatedText;
    }

    // Generic method to generate random email address
    public static String getRandomEmail(String emailPrefix) {
        String generatedEmail = emailPrefix + System.nanoTime() + "@yopmail.com";
        System.out.println("Auto Generated Email is" + " " + generatedEmail);
        return generatedEmail;
    }

    // Generic method for thread.sleep - Web
    public static void findElementSleep(WebDriver driver,Integer milliseconds) throws InterruptedException {
        try{
            Thread.sleep(milliseconds);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
