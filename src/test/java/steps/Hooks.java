package steps;

import io.cucumber.java.Before;
import model.Customer;
import support.core.ExecutionManager;

public class Hooks {

    @Before
    public void setUp(){
        ExecutionManager.getInstance().setCustomer(new Customer());
    }
}
