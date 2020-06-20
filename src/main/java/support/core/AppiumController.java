package support.core;

import lombok.Getter;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Getter
public class AppiumController {

    private URL serverUrl;
    private DesiredCapabilities caps;
    private AppiumService appiumService;

    public AppiumController() {
        appiumService = new AppiumService();
    }

    public void startAppium() {
        try {
            serverUrl = appiumService.startServer();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void stopAppium(){
        if(appiumService != null){
            appiumService.stopServer();
            appiumService = null;
        }
    }
}
