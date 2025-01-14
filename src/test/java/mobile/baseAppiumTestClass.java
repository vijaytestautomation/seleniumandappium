package mobile;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;


import java.io.File;
import java.io.IOException;

import java.util.logging.Logger;

import static support.utils.getConfigParser;

public class baseAppiumTestClass {
    protected static AppiumDriver driver;
    protected String testCaseName;
    private static final Logger logger = Logger.getLogger(baseAppiumTestClass.class.getName());


    static long time = System.currentTimeMillis();
    static ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\src\\test\\reports\\TestReport-" + time + ".html");
    static ExtentReports testReport = new ExtentReports();
    static ExtentTest eTest;

    public baseAppiumTestClass(AppiumDriver driver, String testCaseName) {
        baseAppiumTestClass.driver = driver;
        this.testCaseName = testCaseName;
    }

    protected void AddTestToReport(String testName, String description) {

        testReport.attachReporter(htmlReporter);
        eTest = testReport.createTest(testName, description);
        htmlReporter.config().setDocumentTitle("Automation Test Results");
        htmlReporter.config().setReportName("Test Environment");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
    }

    public void AddDetailsToReport(int status, String description) throws Exception {

        switch (status) {
            case 1:
                eTest.pass(description);
                break;
            case 2:
                eTest.fail(description + eTest.addScreenCaptureFromPath(captureScreen()));
                break;
            case 3:
                eTest.warning(description);
                break;
            case 9:
                eTest.log(Status.INFO, description);
                break;
            case 5:
                eTest.info(description);
                break;
            case 6:
                eTest.skip(description);
                break;
        }
    }

    protected void FlushReport() {
        testReport.flush();
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

    @BeforeClass
    public void setUp() {
        System.out.println("Inside Setup method");
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Inside TearDown method");
    }

    @BeforeSuite
    public void BeforeSuiteSetUp() throws Exception {
        System.out.println("Inside BeforeSuiteSetUp method");
    }

    @AfterSuite
    public void AfterSuiteTearDown() {
        System.out.println("Inside AfterSuiteTearDown method");
        if (driver != null) {
            driver.quit();
        }
    }
}

