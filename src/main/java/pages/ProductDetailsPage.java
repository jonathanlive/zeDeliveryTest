package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import support.core.ExecutionManager;
import support.mobileActions.Actions;

public class ProductDetailsPage extends BasePage{

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"product-title\")")
    private MobileElement productName;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"product-price\")")
    private MobileElement productPrice;

    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"amount\")")
    private MobileElement productAmount;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"plus-button\")")
    private MobileElement btnPlus;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"add-to-checkout\")")
    private MobileElement btnAddItem;

    public void setItemAmount(int amount){
        Actions.clickWithFluentWait(btnPlus,10,amount-1);
    }

    public void clickBtnAddItem(){
        ExecutionManager.getInstance().getCustomer().addCartItem(productName.getText(),productPrice.getText(),productAmount.getText());
        Actions.clickWithFluentWait(btnAddItem,10);
    }
}
