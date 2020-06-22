package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Customer;
import org.junit.Assert;
import pages.AddressPage;
import pages.BasePage;
import pages.DetalhesDoPedidoPage;
import pages.HomePage;
import support.util.CustomerUtil;

public class AddressResearch {

    HomePage homePage = BasePage.getInstance(HomePage.class);
    DetalhesDoPedidoPage detalhesDoPedidoPage = BasePage.getInstance(DetalhesDoPedidoPage.class);
    AddressPage addressPage = BasePage.getInstance(AddressPage.class);
    private Customer customer = CustomerUtil.createNewCustomer();

    @When("user select the address dropdown menu")
    public void userSelectTheAddressDropdownMenu() {
        homePage.clickMenuEditAddress();
    }

    @And("add a new address")
    public void addANewAddress() {
        detalhesDoPedidoPage.clickBtnEditAddress();
        addressPage.setAddress(customer.getAddress().getStreet(),customer.getAddress().getNumber(),customer.getAddress().getCompl(),customer.getAddress().getNeighborhood());
        detalhesDoPedidoPage.clickBtnVerProdutosDisponiveis();
    }

    @Then("homepage will be updated with the new address information")
    public void homepageWillBeUpdatedWithTheNewAddressInformation() {
        Assert.assertTrue(homePage.checkAddress(customer.getAddress().getStreet()));
    }
}
