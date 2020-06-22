package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import support.mobileActions.Actions;

public class DetalhesDoPedidoPage extends BasePage {

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"edit-card\")")
    private MobileElement btnEditAddress;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"see-products\")")
    private MobileElement btnVerProdutosDisponiveis;

    public void clickBtnEditAddress(){
        Actions.clickWithFluentWait(btnEditAddress,10);
    }

    public void clickBtnVerProdutosDisponiveis(){
        Actions.waitForSeconds(3);
        Actions.clickWithFluentWait(btnVerProdutosDisponiveis,10);
    }
}
