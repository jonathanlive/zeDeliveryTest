package support.mobileActions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import model.Direction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.Exception.AutomationException;
import support.core.DriverManager;
import java.time.Duration;
import java.util.List;

public class Actions {

    /**
     * Explicitamente espera por alguns segundos até que a proxima instrucão possa ser executada.
     *
     * @param seconds Tempo limite de espera.
     */
    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new AutomationException(e.getMessage());
        }
    }

    /**
     * Explicitamente espera por alguns segundos até que a proxima instrucão possa ser executada.
     *
     * @param maxTimeWaitInMiliseconds Tempo limite de espera.
     */
    public static void waitForMiliseconds(long maxTimeWaitInMiliseconds) {
        try {
            Thread.sleep(maxTimeWaitInMiliseconds);
        } catch (InterruptedException e) {
            throw new AutomationException(e.getMessage());
        }
    }

    /**
     * Verifica se um determinado elemento é visível.
     *
     * @param e Elemento a ser verificado.
     * @return Retorna verdadeiro ou falso.
     */
    public static boolean isDisplayed(MobileElement e) {
        try {
            return e.isDisplayed();
        } catch (WebDriverException ex) {
            return false;
        }
    }

    public static boolean isEnable(MobileElement e) {
        try {
            return e.isEnabled();
        } catch (WebDriverException ex) {
            return false;
        }
    }

    /**
     * Aguarda até que um determinado elemento esteja visível.
     *
     * @param e                 Elemento a ser verificado.
     * @param maxTimeWaitInSeconds Tempo limite de espera pelo elemento.
     * @return
     */
    public static boolean waitForElement(MobileElement e, int maxTimeWaitInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getInstance().getDriver(), maxTimeWaitInSeconds);
        if (maxTimeWaitInSeconds <= 0)
            throw new AutomationException("Náo é possível declarar tempo de espera menor ou igual a zero segundos");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(e));
        } catch (StaleElementReferenceException e1) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(e));
            } catch (WebDriverException e2) {
                System.out.println("Elemento não localizado: " + e2.getMessage());
                return false;
            }
        } catch (WebDriverException e3) {
            System.out.println("Elemento não localizado: " + e3.getMessage());
            return false;
        }

        return e.isDisplayed();
    }

    public static boolean waitForElements(List<MobileElement> eList, int maxTimeWaitInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getInstance().getDriver(), maxTimeWaitInSeconds);
        if (maxTimeWaitInSeconds <= 0)
            throw new AutomationException("Náo é possível declarar tempo de espera menor ou igual a zero segundos");

        try {
            for (MobileElement e : eList) {
                wait.until(ExpectedConditions.elementToBeClickable(e));
            }
        } catch (StaleElementReferenceException e1) {
            try {
                for (MobileElement e : eList) {
                    wait.until(ExpectedConditions.elementToBeClickable(e));
                }
            } catch (WebDriverException e2) {
                System.out.println("Elemento não localizado: " + e2.getMessage());
                return false;
            }
        } catch (WebDriverException e3) {
            System.out.println("Elemento não localizado: " + e3.getMessage());
            return false;
        }

        for (MobileElement e : eList) {
            if (e.isDisplayed())
                return true;
        }

        return false;
    }

    public static String getElementText(MobileElement element) {
        return element.getText();
    }

    public static Boolean getElementIsDisplayed(MobileElement element) {
        return element.isDisplayed();
    }

    public static void setEditTextValue(MobileElement element, String value) {
        waitForElement(element,3);
        element.clear();
        element.sendKeys(value);
        if (isIOS()){
            MobileElement done = (MobileElement) DriverManager.getInstance().getDriver()
                    .findElement(MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND name == 'Done'"));
            if (Actions.isDisplayed(done))
                done.click();
        }
    }

    public static void setListBoxValue(MobileElement element, String value) {
        element.sendKeys(value);
        MobileElement done = (MobileElement) DriverManager.getInstance().getDriver()
                .findElement(MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND name == 'Done'"));
        if (isIOS() && Actions.isDisplayed(done))
            done.click();
    }

    public static boolean isIOS() {
        String platformName = (String) DriverManager.getInstance().getDriver()
                .getCapabilities().getCapability("platformName");
        return platformName.equalsIgnoreCase("iOS");
    }

    public static void clickWithFluentWait(MobileElement element, int maxTimeWaitInSeconds) {
        waitForElement(element, maxTimeWaitInSeconds);
        if (isDisplayed(element))
            element.click();
    }

    public static void clickWithFluentWait(MobileElement element, int maxTimeWaitInSeconds, int nTimes) {
        for (int i = 0; i < nTimes; i++) {
            waitForElement(element, maxTimeWaitInSeconds);
            element.click();
        }
    }

    public static void swipeToDirection(Direction direction, long durationInMilliseconds, double precision) {
        Dimension size = DriverManager.getInstance().getDriver().manage().window().getSize();

        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;

        switch (direction) {
            case LEFT:
                startY = (int) (size.height / 2.1);
                endY = (int) (size.height / 2.1);
                startX = (int) (size.width * 0.8);
                endX = (int) (size.width - precision);
                break;

            case RIGHT:
                startY = (int) (size.height / 2.1);
                endY = (int) (size.height / 2.1);
                startX = (int) (size.width * 0.11);
                endX = (int) (size.width + precision);
                break;

            case DOWN:
                endY = (int) (size.height + precision);
                startY = (int) (size.height * 0.30);
                startX = (size.width / 2);
                endX = (size.width / 2);
                break;


            case UP:
                startY = (int) (size.height * 0.70);
                endY = (int) (size.height - precision);
                startX = (size.width / 2);
                endX = (size.width / 2);
                break;
        }

        new TouchAction<>(DriverManager.getInstance().getDriver())
                .press(new PointOption().withCoordinates(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationInMilliseconds)))
                .moveTo(new PointOption().withCoordinates(endX, endY))
                .release()
                .perform();

    }

    public static void acceptAlert(int maxTimeWaitInSeconds){
        for (int duration = 0; duration < maxTimeWaitInSeconds; duration++){
            try{
                DriverManager.getInstance().getDriver().switchTo().alert().accept();
                break;
            }catch (NoAlertPresentException e){
                System.out.println("Aguardando alerta: " + duration);
                waitForSeconds(1);
            }
        }
    }

    public static void dismissAlert(int maxTimeWaitInSeconds){
        for (int duration = 0; duration < maxTimeWaitInSeconds; duration++){
            try{
                DriverManager.getInstance().getDriver().switchTo().alert().accept();
                break;
            }catch (NoAlertPresentException e){
                System.out.println("Aguardando alerta: " + duration);
                waitForSeconds(1);
            }
        }
    }
}

