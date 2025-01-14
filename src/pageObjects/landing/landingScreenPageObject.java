package pageObjects.landing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.basePageObjectClass;

// page_url = https://buggy.justtestit.org/
public class landingScreenPageObject extends basePageObjectClass {

    public landingScreenPageObject(WebDriver driver)  throws Exception{
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // WebElements
    @FindBy(css = "a[class$='btn-success-outline']")
    public WebElement linkRegister;

    public void clickRegister(){
        linkRegister.click();
    }
}