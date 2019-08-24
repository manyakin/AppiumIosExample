package settings;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import pages.HomePage;
import pages.LogInPage;
import pages.SignInPage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import static com.google.common.io.Files.toByteArray;


@Listeners({MobileSettings.class})
public class MobileSettings implements ITestListener {

    protected IOSDriver driver;
    protected HomePage objHome;
    protected LogInPage objLogIn;
    protected SignInPage objSignIn;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"device", "wda", "version"})
    public void setUp(final String device, final String wda, final String version) {

        String APP = new File("src/test/resources/app/Wikipedia.app").getAbsolutePath();

        try {

            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setCapability(MobileCapabilityType.APP, APP);
            capability.setCapability(MobileCapabilityType.DEVICE_NAME, device);
            capability.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, wda);
            capability.setCapability(IOSMobileCapabilityType.IOS_INSTALL_PAUSE, 2);
            capability.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
            capability.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS);
            capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), capability);

        } catch (MalformedURLException e) {
            Logger.getLogger("Сессия не запущена");
        }

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /* Создание скриншота */
    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] makeScreen() {
        try {
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            return toByteArray(screenshot);
        } catch (IOException e) {
            Logger.getLogger("Ошибка в методе makeScreen");
        }
        return new byte[0];
    }

    private IOSDriver getDriver() {
        return driver;
    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        driver = ((MobileSettings) currentClass).getDriver();
        makeScreen();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

}
