package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.junit.Assert;
import support.mobileActions.Actions;

public class LoginPage extends BasePage{

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"already-have-an-account\")")
    private MobileElement linkEntrar;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"email\")")
    private MobileElement editorEmail;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"password\")")
    private MobileElement editorPassword;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"enter\")")
    private MobileElement btnEntrar;

    public void checkMainPage(){
        Actions.acceptAlert(15);
        Actions.waitForElement(linkEntrar,10);
        Assert.assertEquals("Já possui uma conta? Entrar", linkEntrar.getText());
    }

    public void selectLinkEntrar(){
        Actions.clickWithFluentWait(linkEntrar,10);
    }

    public void setEmail(String email){
        Actions.setEditTextValue(editorEmail,email);
    }

    public void setPassword(String password){
        Actions.setEditTextValue(editorPassword,password);
    }

    public void clickBtnEntrar(){
        Actions.clickWithFluentWait(btnEntrar,10);
    }

    public void login(String email, String password){
        setEmail(email);
        setPassword(password);
        clickBtnEntrar();
    }
}
