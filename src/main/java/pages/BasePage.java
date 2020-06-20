package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import support.Exception.PageException;
import support.core.DriverManager;

public class BasePage {

    public static AppiumDriver driver;

    public static <T extends BasePage> T getInstance(Class<T> clazz) {
        try {
            driver = DriverManager.getInstance().getDriver();
            T pageObject = clazz.getDeclaredConstructor().newInstance();
            PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getInstance().getDriver()), pageObject);
            return pageObject;
        } catch (Exception e) {
            throw new PageException(e.getMessage());
        }
    }

    public static boolean isIOS() {
        String platformName = (String) DriverManager.getInstance().getDriver()
                .getCapabilities().getCapability("platformName");
        return platformName.equalsIgnoreCase("iOS");
    }
}

