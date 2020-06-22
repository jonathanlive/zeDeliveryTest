package support.core;

import lombok.Getter;
import org.openqa.selenium.remote.DesiredCapabilities;

@Getter
public class CapabilitieManager {

    private static CapabilitieManager instance;
    private String userHome;
    private String deviceName;
    private String platformName;
    private String platformVersion;
    private String appActivity;
    private String appPackage;
    private String automationName;
    private String bundleId = "";

    private CapabilitieManager() {

    }

    public static CapabilitieManager getInstance() {
        if (instance == null)
            instance = new CapabilitieManager();

        return instance;
    }

    public DesiredCapabilities returnCaps() {
        userHome = System.getProperty("user.home");
        platformName = PropertieManager.getInstance().readProperties().getProperty("platformName");
        platformVersion = PropertieManager.getInstance().readProperties().getProperty("platformVersion");
        deviceName = PropertieManager.getInstance().readProperties().getProperty("deviceName");
        appPackage = PropertieManager.getInstance().readProperties().getProperty("appPackage");
        appActivity = PropertieManager.getInstance().readProperties().getProperty("appActivity");
        bundleId = PropertieManager.getInstance().readProperties().getProperty("bundleId");
        automationName = PropertieManager.getInstance().readProperties().getProperty("automationName");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("automationName", automationName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("unicodeKeyboard", "true");
        capabilities.setCapability("resetKeyboard", "true");
        capabilities.setCapability("noReset",false);
        return capabilities;
    }
}