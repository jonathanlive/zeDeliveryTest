package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Customer;
import org.junit.Assert;
import pages.AddressPage;
import pages.BasePage;
import pages.OrderDetailsPage;
import pages.HomePage;
import support.util.CustomerUtil;

public class ChangeAddressSteps {

    HomePage homePage = BasePage.getInstance(HomePage.class);
    OrderDetailsPage orderDetailsPage = BasePage.getInstance(OrderDetailsPage.class);
    AddressPage addressPage = BasePage.getInstance(AddressPage.class);
    private Customer customer = CustomerUtil.createNewCustomer();

    @When("user select the address dropdown menu")
    public void userSelectTheAddressDropdownMenu() {
        homePage.clickMenuEditAddress();
    }

    @And("add a new address")
    public void addANewAddress() {
        orderDetailsPage.clickBtnEditAddress();
        addressPage.setAddress(customer.getAddress().getStreet(),customer.getAddress().getNumber(),customer.getAddress().getCompl(),customer.getAddress().getNeighborhood());
        orderDetailsPage.clickBtnVerProdutosDisponiveis();
    }

    @Then("homepage will be updated with the new address information")
    public void homepageWillBeUpdatedWithTheNewAddressInformation() {
        Assert.assertTrue(homePage.checkAddress(customer.getAddress().getStreet()));
    }
}
