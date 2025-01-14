package test.java.mobile;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.mobile.login.loginScreenPageObject;

import support.log;
import support.utils;
import test.java.register.RegisterTest;

import java.util.logging.Logger;

import static java.util.logging.Level.parse;

public class LoginTest extends mobile.baseAppiumTestClass {
    private static final Logger logger = Logger.getLogger(RegisterTest.class.getName());

    public LoginTest() throws Exception{
        super(utils.getAppiumDriver(),"Launch an application");
    }

    @Test(priority = 1)
    public void validLogin() throws Exception {
        try{
            AddTestToReport("validLogin", "Open application in device and Login");
            logger.info("Successfully launched Application - Inside Valid Login Testcase scenario");
            AddDetailsToReport(9, "Successfully launched Application - Inside Valid Login Testcase scenario");
            loginScreenPageObject loginPage = new loginScreenPageObject(driver);
            loginPage.login("QA.mobileAppTester", "password:Experieco2024!");

        } catch (Exception e) {
            logger.log(parse("Exception in valid login test"), e.getMessage());
            AddDetailsToReport(2,"Exception in valid login test");
            e.printStackTrace();
            throw(e);
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        DOMConfigurator.configure("./src/main/resources/log4j.xml");
        log.startTestCase(testCaseName);
    }

    @AfterMethod
    public void afterMethod() {
        log.endTestCase(testCaseName);
        FlushReport();
    }
}
