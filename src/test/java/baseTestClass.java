package test.java;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import support.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static java.util.logging.Level.*;
import static support.utils.getConfigParser;

/*
 * This must be extended by all test classes
 */

public class baseTestClass {
    public WebDriver driver;
    protected String testCaseName;
    public static String selectedElementValue;

    static long time = System.currentTimeMillis();
    static ExtentSparkReporter htmlReporter = new ExtentSparkReporter( System.getProperty("user.dir") + "\\src\\test\\reports\\BuggyTest-" + time + ".html");
    static ExtentReports testReport = new ExtentReports();
    static ExtentTest eTest;

    public baseTestClass(WebDriver driver, String testcaseName) {
        this.driver = driver;
        this.testCaseName = testcaseName;
    }

    protected void AddTestToReport(String testName, String description) throws Exception {

        if(getConfigParser().getPropertyValue("testEnvironment").equalsIgnoreCase("test")){
            testReport.attachReporter(htmlReporter);
            eTest = testReport.createTest(testName, description);
            htmlReporter.config().setDocumentTitle("BuggyTest- Automation Test Results");
            htmlReporter.config().setReportName("Test Environment");
            htmlReporter.config().setTheme(Theme.DARK);
            htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        }
    }

    public void AddDetailsToReport(int status, String description) throws Exception {

        switch (status){
            case 1: eTest.pass(description);
                break;
            case 2: eTest.fail(description + eTest.addScreenCaptureFromPath(captureScreen()));
                break;
            case 3: eTest.warning(description);
                break;
            case 9: eTest.log(Status.INFO, description);
                break;
            case 5: eTest.info(description);
                break;
            case 6: eTest.skip(description);
                break;
        }
    }

    protected void FlushReport(){
        testReport.flush();
    }

    /* Code to handle Javascript errors on screen*/
    public void ExtractJSLogs() throws Exception {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        if (logEntries.getAll().isEmpty()){
            System.out.println("Console output from browser:" + logEntries );
            AddDetailsToReport(1, "No Javascript errors");
        }
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            if(entry.getLevel() == SEVERE) {
                AddDetailsToReport(4, "Javascript Information on WebPage are: " + new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            }else if(entry.getLevel() == FINE){
                AddDetailsToReport(1, "FINE Javascript Logs on WebPage are: " + new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            }else if(entry.getLevel() == WARNING){
                AddDetailsToReport(3, "WARNING Javascript Logs on WebPage are: " + new Date(entry.getTimestamp()) + " " + WARNING + " " + entry.getMessage());
            }else if(entry.getLevel() == INFO){
                AddDetailsToReport(9, "INFO Javascript Logs on WebPage are: " + new Date(entry.getTimestamp()) + " " + INFO + " " + entry.getMessage());
            }
        }
    }

    public String captureScreen() throws IOException, Exception {
        TakesScreenshot screen = (TakesScreenshot) driver;
        File src = screen.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") + getConfigParser().getPropertyValue("saveScreenshotPath")
                + getConfigParser().getPropertyValue("saveScreenshotFileExtension");
        File target = new File(dest);
        FileUtils.copyFile(src, target);
        return dest;
    }

    // Method to select a value from dropdown with div
    public void selectValueFromDiv(String value, String cssVal, String selectElement) throws Exception {

        List<WebElement> elements = driver.findElements(By.cssSelector(cssVal));
        System.out.println("Elements in component:" + " " + elements);
        //'selecting a value'
        for(WebElement element : elements){
            if(element.getText().equalsIgnoreCase(value)){
                element.click();
                break;
            }
        }
        Select dropdown = new Select(driver.findElement(By.cssSelector(selectElement)));
        WebElement dropdownElement = dropdown.getFirstSelectedOption();
        selectedElementValue= dropdownElement.getText();
        System.out.println("Selected value is:" + " " + selectedElementValue);
        AddDetailsToReport(9,"Selected value is:" + " " + selectedElementValue);
    }

    @BeforeClass
    public void setUp(){
        System.out.println("Inside Setup method");
    }

    @AfterClass
    public void tearDown(){
        System.out.println("Inside TearDown method");
        driver.quit();
    }

    @BeforeSuite
    public void BeforeSuiteSetUp() throws Exception {
        String url = null;
        try {
            System.out.println("Inside BeforeSuiteSetUp method");
            driver.get(utils.getApplicationURL());
            // URL health check
            url = driver.getCurrentUrl();
            System.out.println("Attempt to access:" + url);
            Assert.assertEquals(url, utils.getApplicationURL());
        } catch (AssertionError e) {
            driver.quit();
            System.out.println("URL check failed. Expected: " + utils.getApplicationURL() + ", but got: " + url);
            throw e;
        }
    }

    @AfterSuite
    public void AfterSuiteTearDown(){
        System.out.println("Inside AfterSuiteTearDown method");
//        driver.quit();
    }

}
