package support;

import org.openqa.selenium.By;

import java.io.IOException;
import java.util.Properties;

public class repositoryparser {
    private String repositoryFileName = null;
    private Properties repositoryFile = new Properties();

    public repositoryparser(String fileName) throws IOException
    {
        if (this.repositoryFileName == null) {
            this.repositoryFileName = fileName;
            repositoryFile.load(getClass().getResourceAsStream(repositoryFileName));
        }
    }

    public By getObjectLocator(String elementName)
    {
        String locatorProperty = repositoryFile.getProperty(elementName);
        System.out.println(locatorProperty);
        String locatorType = locatorProperty.split(":")[0];
        String locatorValue = locatorProperty.split(":")[1];

        return switch (locatorType) {
            case constant.LOCATOR_ID -> By.id(locatorValue);
            case constant.LOCATOR_NAME -> By.name(locatorValue);
            case constant.LOCATOR_CSSSELECTOR -> By.cssSelector(locatorValue);
            case constant.LOCATOR_LINKTEXT -> By.linkText(locatorValue);
            case constant.LOCATOR_PARTIALLINKTEXT -> By.partialLinkText(locatorValue);
            case constant.LOCATOR_TAGNAME -> By.tagName(locatorValue);
            case constant.LOCATOR_XPATH -> By.xpath(locatorValue);
            default -> null;
        };
    }
}
