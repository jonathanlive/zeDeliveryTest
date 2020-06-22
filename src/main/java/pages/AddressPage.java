package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import support.mobileActions.Actions;

public class AddressPage extends BasePage {

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"localização\")")
    private MobileElement btnMinhaLocalizacao;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"address-map-button\")")
    private MobileElement btnConfirmarLocal;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"street-input\")")
    private MobileElement editorStreetAddress;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
    private MobileElement editorStreetNumber;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"complement-input\")")
    private MobileElement editorSecondaryAddress;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"neighborhood-input\")")
    private MobileElement editorAddressNeighborhood;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"set-address-submit\")")
    private MobileElement btnContinuar;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"address input\")")
    private MobileElement editorAddress;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"home-button\")")
    private MobileElement btnCasa;

    public void selectLinkUsarMinhaLocalizacao() {
        Actions.clickWithFluentWait(btnMinhaLocalizacao, 10);
    }

    public void clickBtnConfirmarLocal() {
        Actions.clickWithFluentWait(btnConfirmarLocal, 10);
    }

    public void setStreetAddress(String streetAddress) {
        Actions.waitForSeconds(3);
        Actions.setEditTextValue(editorStreetAddress, streetAddress);
    }

    public void setStreetNumber(String streetNumber){
        Actions.setEditTextValue(editorStreetNumber,streetNumber);
    }

    public void setSecondaryAddress(String secondaryAddress) {
        Actions.setEditTextValue(editorSecondaryAddress, secondaryAddress);
    }

    public void setAddressNeighborhood(String addressNeighborhood) {
        Actions.setEditTextValue(editorAddressNeighborhood, addressNeighborhood);
    }

    public void clickBtnContinuar() {
        Actions.clickWithFluentWait(btnContinuar, 10);
    }

    public void setAddress(String streetAddress,String streetNumber, String secondaryAddress, String addressNeighborhood) {
        selectLinkUsarMinhaLocalizacao();
        clickBtnConfirmarLocal();
        setStreetAddress(streetAddress);
        setStreetNumber(streetNumber);
        setSecondaryAddress(secondaryAddress);
        setAddressNeighborhood(addressNeighborhood);
        clickBtnContinuar();
    }
}
