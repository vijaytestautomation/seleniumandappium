package pageObjects.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class baseAppiumPageObjectClass {
    protected static AppiumDriver driver;
    protected static boolean result;
    private static final int TIMEOUT = 20;

    public baseAppiumPageObjectClass(AppiumDriver driver) {
        baseAppiumPageObjectClass.driver = driver;
        baseAppiumPageObjectClass.result = true;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(TIMEOUT)), this);
    }
}
