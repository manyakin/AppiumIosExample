package settings;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class MobileElements extends MobileMethods {

    public MobileElements(IOSDriver driver) {
        super(driver);
    }

    public static By button(final String name) {
        return MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND name == '" + name + "'");
    }

    public static By link(final String name) {
        return MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeLink' AND name CONTAINS '" + name + "'");
    }

    public static By field(final String name) {
        return MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeTextField' AND value == '" + name + "'");
    }

    public static By text(final String name) {
        return MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND label == '" + name + "'");
    }

    public static By password(final String name) {
        return MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeSecureTextField' AND value == '" + name + "'");
    }

    public static By textContains(final String name) {
        return MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value CONTAINS '" + name + "'");
    }

}
