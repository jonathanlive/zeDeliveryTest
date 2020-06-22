package support.core;

import model.Customer;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ExecutionManager {

    private AppiumController appiumController;
    private DesiredCapabilities caps;
    private static ExecutionManager instance;
    private ThreadLocal<Customer> customer = new ThreadLocal<>();

    private ExecutionManager() {

    }

    public static ExecutionManager getInstance() {
        if (instance == null)
            instance = new ExecutionManager();

        return instance;
    }

    public void startExecution() {
        appiumController = new AppiumController();
        caps = CapabilitieManager.getInstance().returnCaps();
        setupDevice();
    }

    public void setupDevice() {
        appiumController.startAppium();
        DriverManager.getInstance().startDriver(appiumController.getServerUrl(), caps);
    }

    public void stopExecution() {
        DriverManager.getInstance().stopDriver();
        appiumController.stopAppium();
    }

    public void setCustomer(Customer customer) {
        this.customer.set(customer);
    }

    public Customer getCustomer() {
        return customer.get();
    }
}


