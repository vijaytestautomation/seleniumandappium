package pageObjects.mobile.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.mobile.baseAppiumPageObjectClass;
import support.utils;

public class loginScreenPageObject extends baseAppiumPageObjectClass {

    public loginScreenPageObject(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    // WebElements
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
    private WebElement email;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
    private WebElement password;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Sign in\")")
    private WebElement signInButton;


    public void login(String email, String password) {
        utils.waitForVisibilityOfTheElement(driver,
                By.xpath("//android.view.View[@text=\"Sign in with your email address\"]/android.view.View[1]/android.widget.EditText"));
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        this.signInButton.click();
    }


}
