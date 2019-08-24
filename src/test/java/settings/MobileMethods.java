package settings;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class MobileMethods extends MobileSettings {

    public MobileMethods(IOSDriver driver) {this.driver = driver;}

    private boolean isElementPresent(final By by) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
            driver.findElement(by);
            driver.manage().timeouts().implicitlyWait(3, SECONDS);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void swipeVerticalDown() {
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "up");
        driver.executeScript("mobile: swipe", params);
    }

    protected void waitForElement(final By by) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void type(final By by, final String text) {
        driver.findElement(by).click();
        driver.findElement(by).sendKeys(text);
    }

    protected void click(final By by) {
        waitForElement(by);
        driver.findElement(by).click();
    }

    protected void hide(final By element) {
        Assert.assertFalse(isElementPresent(element), "Не скрыт элемент " + element + "");
    }

    protected void check(final By element) {
        Assert.assertTrue(isElementPresent(element), "Не отображен элемент " + element + "");
    }

    protected void equalText(final By element, final String text) {
        waitForElement(element);
        String getText = driver.findElement(element).getText();
        Assert.assertTrue(getText.contains(text), "Не совпадает текст " + getText + " и результат " + text + "");
    }

    protected void equalStatus(final By element, final String status) {
        String getAttribute = driver.findElement(element).getAttribute("enabled");
        Assert.assertTrue(getAttribute.contains(status), "Не совпадает статус " + getAttribute + " и результат " + status + "");
    }

}
