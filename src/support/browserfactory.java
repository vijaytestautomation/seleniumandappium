package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class browserfactory {
    private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

    /**
     * Factory method for getting browsers
     */

    public static WebDriver getBrowser(String browserName) {
        WebDriver driver = null;
        switch (browserName) {
            case constant.CHROME_BROWSER:
                driver = drivers.get(constant.CHROME_BROWSER);
                if(driver == null) {
                    ChromeOptions options = new ChromeOptions();
                    driver = new ChromeDriver(options);
                    options.addArguments("--remote-allow-origins=*");
                    driver.manage().window().maximize();
                    options.setAcceptInsecureCerts(true);
                    drivers.put(constant.CHROME_BROWSER, driver);

                } else {
                    driver.navigate().refresh();
                }
                break;

            case constant.FIREFOX_BROWSER:
                driver = drivers.get(constant.FIREFOX_BROWSER);
                if(driver == null) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.setAcceptInsecureCerts(true);
                    options.addArguments("--remote-allow-origins=*");
                    driver = new FirefoxDriver(options);
                    drivers.put(constant.FIREFOX_BROWSER, driver);
                    driver.manage().window().maximize();
                } else {
                    driver.navigate().refresh();
                }
                break;
        }
        return driver;
    }
}