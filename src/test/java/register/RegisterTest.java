package test.java.register;

import org.apache.log4j.xml.DOMConfigurator;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.landing.landingScreenPageObject;
import pageObjects.register.registerScreenPageObject;
import support.jsonReader;
import support.log;
import support.utils;
import test.java.baseTestClass;

import java.util.logging.Logger;

import static java.util.logging.Level.parse;

public class RegisterTest extends baseTestClass {
private static final Logger logger = Logger.getLogger(RegisterTest.class.getName());
String registerTestData = System.getProperty("user.dir") + "\\src\\testData\\register\\register.json";

    public RegisterTest() throws Exception{
        super(utils.getBrowserDriver(utils.getBrowserName()), "Navigate to application and register new user");
    }

    @Test(priority = 1)
    public void register() throws Exception{
        try {
            AddTestToReport("Register new user", "Open application in web browser and register new user");
            logger.info("Successfully launched an application");
            AddDetailsToReport(1, "Successfully launched an application:" +
                    " " + driver.getCurrentUrl());
            landingScreenPageObject landingPage = new landingScreenPageObject(driver);
            landingPage.clickRegister(); // Click Register

           // Read data from registerTestData
           jsonReader jsonReader = new jsonReader(registerTestData);
           String registerSuccessText = jsonReader.getValue("successMessage");
           String newLogin = utils.getRandomNumber("BVTTest");
           // Fill register details
           registerScreenPageObject registerPage = new registerScreenPageObject(driver);
           registerPage.enterRegisterDetails(
                   newLogin,
                   jsonReader.getValue("firstName"),
                   jsonReader.getValue("lastName"),
                   jsonReader.getValue("password"),
                   jsonReader.getValue("confirmPassword")
            );
           registerPage.clickButtonRegister();
           String successMessage = registerPage.retrieveRegisterSuccessMessage();
           // Assert success message
           Assert.assertEquals(successMessage, registerSuccessText );

           AddDetailsToReport(1, "Successfully able to register new user:" +
                   " " + newLogin);

        } catch (Exception e) {
            logger.log(parse("Exception in register test"), e.getMessage());
            AddDetailsToReport(2,"Exception in register test");
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
