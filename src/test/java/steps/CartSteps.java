package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Category;
import pages.BasePage;
import pages.CartPage;
import pages.HomePage;
import pages.ProductDetailsPage;

public class CartSteps {

    HomePage homePage = BasePage.getInstance(HomePage.class);
    ProductDetailsPage productDetailsPage = BasePage.getInstance(ProductDetailsPage.class);
    CartPage cartPage = BasePage.getInstance(CartPage.class);

    @When("the user selects a category (.*)")
    public void theUserSelectsACategoryCategory(String category) {
        cartPage.clearCart();
        Category newCategory = Category.valueOf(category.toUpperCase());
        homePage.selectCategory(newCategory);
    }

    @And("selects the first item on the list")
    public void selectsTheFirstItemOnTheList() {
        homePage.selectFirstProduct();
    }

    @And("add a specific amount {int} to the cart")
    public void addASpecificAmountAmountToTheCart(int amount) {
        productDetailsPage.setItemAmount(amount);
        productDetailsPage.clickBtnAddItem();
    }

    @Then("the cart will be updated with the selected item and correct amount {int}")
    public void theCartWillBeUpdatedWithTheSelectedItemAndCorrectAmountAmount(int amount) {
        cartPage.clickBtnVerSacola();
        cartPage.checkFirstProductDetails(amount);
    }
}
