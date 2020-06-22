package pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.junit.Assert;
import support.core.ExecutionManager;
import support.mobileActions.Actions;

import java.util.List;

public class CartPage extends BasePage {

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"VER SACOLA\")")
    private MobileElement btnVerSacola;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"product-card\")")
    private List<MobileElement> productList;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"primary-modal-button\")")
    private MobileElement btnRemoverProduto;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"button-keep-buying\")")
    private MobileElement btnContinuarComprando;

    public void clickBtnVerSacola() {
        Actions.clickWithFluentWait(btnVerSacola, 10);
    }

    public boolean isCartEmpty() {
        return Actions.isDisplayed(btnVerSacola) ? false : true;
    }

    public void deleteCartItem(int index) {
        MobileElement btnDelete = (MobileElement) productList.get(index).findElement(MobileBy.AndroidUIAutomator("new UiSelector().description(\"delete-product\")"));
        Actions.clickWithFluentWait(btnDelete, 10);
    }

    public void checkFirstProductDetails(int productAmout) {
        String productName = ExecutionManager.getInstance().getCustomer().getCartItem(0).getName();
        MobileElement itemName = null;
        MobileElement itemAmount = null;
        if (productList.size() > 0) {
            itemName = (MobileElement) productList.get(0).findElement(MobileBy.AndroidUIAutomator("new UiSelector().description(\"product-card-title\")"));
            itemAmount = (MobileElement) productList.get(0).findElement(MobileBy.AndroidUIAutomator("new UiSelector().descriptionContains(\"amount\")"));
            Assert.assertEquals(productName, itemName.getText());
            Assert.assertEquals(productAmout,Integer.parseInt(itemAmount.getText()));
        }else{
            Assert.fail("no products found on cart");
        }
    }

    public void clickBtnRemoverProduto() {
        Actions.clickWithFluentWait(btnRemoverProduto, 10);
    }

    public void clickBtnContinuarComprando() {
        Actions.clickWithFluentWait(btnContinuarComprando, 10);
    }

    public void clearCart() {
        if (!isCartEmpty()) {
            clickBtnVerSacola();
            deleteCartItem(0);
            clickBtnRemoverProduto();
            clickBtnContinuarComprando();
        }
    }
}
