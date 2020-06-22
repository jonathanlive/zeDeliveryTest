package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import model.Category;
import model.Direction;
import org.junit.Assert;
import org.openqa.selenium.By;
import support.mobileActions.Actions;
import support.mobileActions.AndroidActions;

import java.util.List;

public class HomePage extends BasePage {

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"menu-edit-address\")")
    private MobileElement menuEditAddress;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"banner\")")
    private MobileElement banner;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"category-menu-Cervejas\")")
    private MobileElement menuCervejas;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"category-menu-Destilados\")")
    private MobileElement menuDestilados;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"category-menu-Vinhos\")")
    private MobileElement menuVinhos;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"category-menu-Sem álcool\")")
    private MobileElement menuSemAlcool;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"category-menu-Petiscos\")")
    private MobileElement menuPetiscos;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"category-menu-Outros\")")
    private MobileElement menuOutros;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"product-card-title\").instance(0)")
    private MobileElement firstProduct;

    public void checkHomePage() {
        Actions.waitForSeconds(10);
        Assert.assertTrue(Actions.isDisplayed(banner));
    }

    public void clickMenuEditAddress() {
        Actions.clickWithFluentWait(menuEditAddress, 10);
    }

    public Boolean checkAddress(String address) {
        Actions.waitForSeconds(5);
        List<MobileElement> txtList = menuEditAddress.findElements(By.className("android.widget.TextView"));
        for (MobileElement txt : txtList) {
            if (txt.getText().contains(address))
                return true;
        }

        return false;
    }

    public void selectCategory(Category category) {
        Actions.swipeToDirection(Direction.UP,1500,1000);
        switch (category) {
            case CERVEJAS:
                Actions.clickWithFluentWait(menuCervejas, 10);
                break;
            case DESTILADOS:
                Actions.clickWithFluentWait(menuDestilados, 10);
                break;
            case VINHOS:
                Actions.clickWithFluentWait(menuVinhos, 10);
                break;
            case SEMALCOOL:
                Actions.clickWithFluentWait(menuSemAlcool, 10);
                break;
            case PETISCOS:
                Actions.clickWithFluentWait(menuPetiscos, 10);
                break;
            case OUTROS:
                Actions.clickWithFluentWait(menuOutros, 10);
                break;
            default:
                Assert.fail("Categoria informado não é válida: " + category);
                break;
        }
    }

    public void selectFirstProduct() {
        Actions.swipeToDirection(Direction.UP,2000,1000);
        Actions.clickWithFluentWait(firstProduct, 10);
    }
}
