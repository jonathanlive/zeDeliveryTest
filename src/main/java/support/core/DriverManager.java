package support.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    public static DriverManager instance;
    private URL url;
    private DesiredCapabilities cap;
    private String platformName;
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    private DriverManager() {

    }

    public static DriverManager getInstance() {
        if (instance == null)
            instance = new DriverManager();

        return instance;
    }

    public void startDriver(URL url, DesiredCapabilities cap) {
        this.url = url;
        this.cap = cap;
        this.platformName = cap.getPlatform().name();

        if (platformName.equalsIgnoreCase("ios"))
            driver.set(new IOSDriver(url, cap));
        else
            driver.set(new AndroidDriver(url, cap));

        setDefaultTimeOut();
    }

    public void closeApp() {
        driver.get().closeApp();
    }

    public void stopDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public AppiumDriver getDriver() {
        if (driver.get() == null) {
            startDriver(url, cap);
        }

        if (getApplicationState() == 0 || getApplicationState() == 1)
            driver.get().launchApp();

        return driver.get();
    }

    public void setDefaultTimeOut() {
        AppiumDriver driver1 = driver.get();
        wait.set(new WebDriverWait(driver1, 5)); //seta timeout default
        driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public Integer getApplicationState() {
        try {
            return driver.get().queryAppState(CapabilitieManager.getInstance().getBundleId()).ordinal();
        } catch (Exception ex) {
            return 0;
        }
    }
}
