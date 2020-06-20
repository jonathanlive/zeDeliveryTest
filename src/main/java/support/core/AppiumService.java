package support.core;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;

public class AppiumService {

    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private DesiredCapabilities cap;
    private String userHome;
    private String osName;

    public URL startServer() throws IOException {

        userHome = System.getProperty("user.home");
        osName = System.getProperty("os.name");
        cap = new DesiredCapabilities();
        builder = new AppiumServiceBuilder();

        if (osName.equalsIgnoreCase("windows 10"))
            builder.withAppiumJS(new File(userHome + "\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"));
        if (osName.toLowerCase().contains("mac"))
            builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"));

        String platform = PropertieManager.getInstance().readProperties().getProperty("platformName");
        if (platform.equalsIgnoreCase("android"))
            builder.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, String.valueOf(findRandomOpenPortOnAllLocalInterfaces()));

        builder.usingAnyFreePort();
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.RELAXED_SECURITY);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "info");
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        return service.getUrl();
    }

    public void stopServer() {
        if (service != null)
            service.stop();
    }

    public boolean checkIfServerIsRunning(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }

        return isServerRunning;
    }

    private Integer findRandomOpenPortOnAllLocalInterfaces() throws IOException {
        try (ServerSocket socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        }
    }
}
