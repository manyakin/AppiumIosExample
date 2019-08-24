package pages;

import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import settings.MobileMethods;

import static settings.MobileElements.*;

public class SignInPage extends MobileMethods {
    public SignInPage(IOSDriver driver) {super(driver);}

    private final By REGISTRATION_CLOSE = button("Close");
    private final By LOGIN_FIELD_TITLE = text("Username");
    private final By LOGIN_FIELD = field("enter username");
    private final By CAPTCHA_FIELD = field("CAPTCHA text");
    private final By PASSWORD_FIELD_TITLE = text("Password");
    private final By RESET_FIELD_BUTTON = button("clear mini");
    private final By MAIL_FIELD = field("example@example.org");
    private final By PASSWORD_FIELD = password("enter password");
    private final By MAIL_FIELD_TITLE = text("Email (optional)");
    private final By LOGIN_VALIDATE = text("Username not available");
    private final By LOGIN_CLASS = By.className("XCUIElementTypeTextField");
    private final By REGISTRATION_FORM = text("Create a new account");
    private final By REGISTRATION_BUTTON = button("Create your account");
    private final By CAPTCHA_FIELD_TITLE = text("CAPTCHA security check");
    private final By CONFIRM_PASSWORD_FIELD = password("re-enter password");
    private final By CONFIRM_PASSWORD_FIELD_TITLE = text("Confirm password");
    private final By LOGIN_BUTTON = text("Already have an account? Log in.");
    private final By PASSWORD_CLASS = By.className("XCUIElementTypeSecureTextField");

    public enum Status {
        ENABLED("true"),
        DISABLED("false");

        public String description;
        Status(String description) {
            this.description = description;
        }
        public String getDescription() {return description;}
    }

    public enum Credentials {
        NAME("Iddqd"),
        ERROR("1234");

        public String description;
        Credentials(String description) {
            this.description = description;
        }
        public String getDescription() {return description;}
    }

    @Step("Проверка статуса кнопки регистрации")
    public void checkStatusSignInButton(Status status) {
        swipeVerticalDown();
        equalStatus(REGISTRATION_BUTTON, status.description);
    }

    @Step("Отображение ошибки валидации поля - Логин")
    public void validatePhoneField() {
        waitForElement(LOGIN_VALIDATE);
    }

    @Step("Отображение элементов формы регистрации")
    public void viewSignInForm() {
        waitForElement(REGISTRATION_FORM);
        check(MAIL_FIELD);
        check(LOGIN_FIELD);
        check(LOGIN_BUTTON);
        check(PASSWORD_FIELD);
        check(MAIL_FIELD_TITLE);
        check(LOGIN_FIELD_TITLE);
        check(REGISTRATION_CLOSE);
        check(RESET_FIELD_BUTTON);
        check(CAPTCHA_FIELD_TITLE);
        check(PASSWORD_FIELD_TITLE);
        check(CONFIRM_PASSWORD_FIELD);
        check(CONFIRM_PASSWORD_FIELD_TITLE);

        swipeVerticalDown();
        check(CAPTCHA_FIELD);
        check(REGISTRATION_BUTTON);
    }

    @Step("Сохранение формы")
    public SignInPage saveForm() {
        click(REGISTRATION_BUTTON);
        return this;
    }

    @Step("Заполнение формы регистрации")
    public SignInPage fillForm(Credentials login) {
        waitForElement(REGISTRATION_FORM);
        type(LOGIN_FIELD, login.description);
        type(PASSWORD_FIELD, login.description);
        type(CONFIRM_PASSWORD_FIELD, login.description);

        swipeVerticalDown();
        type(CAPTCHA_FIELD, login.description);
        return this;
    }

    @Step("Переход на форму авторизации")
    public void checkOpenAuth() {
        click(LOGIN_BUTTON);
    }

    @Step("Очистка поля логин через кнопку сброса")
    public void clearLoginFieldButton() {
        waitForElement(REGISTRATION_FORM);
        type(LOGIN_FIELD, Credentials.ERROR.description);
        click(RESET_FIELD_BUTTON);
        equalText(LOGIN_CLASS, "enter username");
    }

    @Step("Очистка поля пароль через кнопку сброса")
    public void clearPasswordFieldButton() {
        waitForElement(REGISTRATION_FORM);
        type(PASSWORD_FIELD, Credentials.ERROR.description);
        click(RESET_FIELD_BUTTON);
        equalText(PASSWORD_CLASS, "enter password");
    }

    @Step("Очистка поля подтверждения пароля через кнопку сброса")
    public void clearConfirmPasswordFieldButton() {
        waitForElement(REGISTRATION_FORM);
        type(CONFIRM_PASSWORD_FIELD, Credentials.ERROR.description);
        click(RESET_FIELD_BUTTON);
        check(CONFIRM_PASSWORD_FIELD);
    }

}
