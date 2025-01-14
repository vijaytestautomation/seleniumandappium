package pageObjects.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.basePageObjectClass;
import support.utils;


public class registerScreenPageObject extends basePageObjectClass {
   

    public registerScreenPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // WebElements
    @FindBy(css = "#username")
    public WebElement inputUsername;

    @FindBy(css = "#firstName")
    public WebElement inputFirstName;

    @FindBy(css = "#lastName")
    public WebElement inputLastName;

    @FindBy(css = "#password")
    public WebElement inputPassword;

    @FindBy(css = "#confirmPassword")
    public WebElement inputConfirmPassword;

    @FindBy(css = "button[class$='btn-default']")
    public WebElement buttonRegister;

    @FindBy(css = "div[class$='alert-success']")
    public WebElement label_registerSuccessMessage;

    

    public void enterRegisterDetails(String inputUsername, String inputFirstName, String inputLastName, String inputPassword, String inputConfirmPassword) {
        this.inputUsername.sendKeys(inputUsername);
        this.inputFirstName.sendKeys(inputFirstName);
        this.inputLastName.sendKeys(inputLastName);
        this.inputPassword.sendKeys(inputPassword);
        this.inputConfirmPassword.sendKeys(inputConfirmPassword);
    }

    public void clickButtonRegister() {
        utils.waitForClickableOfTheElement(driver, By.cssSelector("button[class$='btn-default']") );
        this.buttonRegister.click();
    }

    public String retrieveRegisterSuccessMessage() {
        utils.waitForVisibilityOfTheElement(driver, By.cssSelector("div[class$='alert-success']") );
        return label_registerSuccessMessage.getText();

    }
    
    
    
    
}