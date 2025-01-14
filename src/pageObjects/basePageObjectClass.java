package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

/*
 * This must be extended by all pageObject classes
 */
public class basePageObjectClass {
    protected  static WebDriver driver;
    protected  static boolean result;
    private static final int TIMEOUT = 20;

    public basePageObjectClass(WebDriver driver) {
        basePageObjectClass.driver = driver;
        basePageObjectClass.result = true;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    public void selectValueFromDiv(String value, String XpathVal) throws Exception {

        List<WebElement> elements = driver.findElements(By.xpath(XpathVal));
        System.out.println("Elements in component:" + " " + elements.size());
        //'selecting a value'
        for(WebElement element : elements){
            if(element.getText().equalsIgnoreCase(value)){
                element.click();
                break;
            }
        }
    }
}