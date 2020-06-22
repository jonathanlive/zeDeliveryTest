package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Customer;
import pages.*;
import support.core.PropertieManager;
import support.util.CustomerUtil;

public class LoginSteps {

    LoginPage loginPage = BasePage.getInstance(LoginPage.class);
    OrderDetailsPage orderDetailsPage = BasePage.getInstance(OrderDetailsPage.class);
    AddressPage addressPage = BasePage.getInstance(AddressPage.class);
    HomePage homePage = BasePage.getInstance(HomePage.class);

    private Customer customer = CustomerUtil.createNewCustomer();

    @Given("user is on the main page")
    public void userIsOnTheMainPage() {
        loginPage.checkMainPage();
    }

    @When("user login with email (.*) and password (.*)")
    public void userLoginWithEmailEmailAndPasswordPassword(String email, String password) {
        loginPage.selectLinkEntrar();
        loginPage.login(email, password);
    }

    @And("confirm the address location")
    public void confirmTheAdressLocation() {
        addressPage.setAddress(customer.getAddress().getStreet(),customer.getAddress().getNumber(), customer.getAddress().getCompl(), customer.getAddress().getNeighborhood());
        orderDetailsPage.clickBtnVerProdutosDisponiveis();
    }

    @Then("homepage will be displayed")
    public void homepageWillBeDisplayed() {
        homePage.checkHomePage();
    }

    @Given("user is on the homepage")
    public void userIsOnTheHomepage() {
        String defaultEmail = PropertieManager.getInstance().readProperties().getProperty("defaultEmail");
        String defaultPassword = PropertieManager.getInstance().readProperties().getProperty("defaultPassword");
        loginPage.checkMainPage();
        loginPage.selectLinkEntrar();
        loginPage.login(defaultEmail, defaultPassword);
        addressPage.setAddress(customer.getAddress().getStreet(),customer.getAddress().getNumber(), customer.getAddress().getCompl(), customer.getAddress().getNeighborhood());
        orderDetailsPage.clickBtnVerProdutosDisponiveis();
        homePage.checkHomePage();
    }
}
