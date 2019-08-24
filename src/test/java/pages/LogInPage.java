package pages;

import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import settings.MobileMethods;

import static settings.MobileElements.*;

public class LogInPage extends MobileMethods {
    public LogInPage(IOSDriver driver) {super(driver);}

    private final By LOGIN_CLOSE = button("Close");
    private final By LOGIN_BUTTON = button("Log in");
    private final By LOGIN_FIELD_TITLE = text("Username");
    private final By PASSWORD_FIELD_TITLE = text("Password");
    private final By RESET_FIELD_BUTTON = button("clear mini");
    private final By LOGIN_FORM = text("Log in to your account");
    private final By PASSWORD_VALIDATE = text("Invalid password");
    private final By RECOVERY_BUTTON = text("Forgot your password?");
    private final By LOGIN_FIELD = By.className("XCUIElementTypeTextField");
    private final By LOGIN_FIELD_PLACEHOLDER = field("enter username");
    private final By PASSWORD_FIELD_PLACEHOLDER = password("enter password");
    private final By PASSWORD_FIELD = By.className("XCUIElementTypeSecureTextField");
    private final By REGISTERED_BUTTON = textContains("have an account? Join Wikipedia.");
    private final By LOGIN_VALIDATE = text("The supplied credentials could not be authenticated.");

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
        CORRECT("iddqd"),
        ERROR(" ");

        public String description;
        Credentials(String description) {
            this.description = description;
        }
        public String getDescription() {return description;}
    }

    public enum Password {
        CORRECT("Qwer1234"),
        INCORRECT("Q");

        public String description;
        Password(String description) {
            this.description = description;
        }
        public String getDescription() {return description;}
    }

    @Step("Отображение элементов формы входа")
    public void viewLoginCard() {
        waitForElement(LOGIN_FORM);
        check(LOGIN_CLOSE);
        check(LOGIN_BUTTON);
        check(RECOVERY_BUTTON);
        check(LOGIN_FIELD_TITLE);
        check(REGISTERED_BUTTON);
        check(RESET_FIELD_BUTTON);
        check(PASSWORD_FIELD_TITLE);
        check(LOGIN_FIELD_PLACEHOLDER);
        check(PASSWORD_FIELD_PLACEHOLDER);
    }

    @Step("Проверка статуса кнопки входа")
    public void checkStatusLogInButton(Status status) {
        waitForElement(LOGIN_FORM);
        equalStatus(LOGIN_BUTTON, status.description);
    }

    @Step("Заполнение формы авторизации")
    public LogInPage fillLogIn(Credentials login, Password password) {
        waitForElement(LOGIN_FORM);
        type(LOGIN_FIELD_PLACEHOLDER, login.description);
        type(PASSWORD_FIELD_PLACEHOLDER, password.description);
        return this;
    }

    @Step("Сохранение формы авторизации")
    public LogInPage saveForm() {
        click(LOGIN_BUTTON);
        return this;
    }

    @Step("Очистка поля логина через кнопку сброса")
    public void clearLoginFieldButton() {
        click(LOGIN_FIELD);
        click(RESET_FIELD_BUTTON);
        equalText(LOGIN_FIELD, "enter username");
    }

    @Step("Очистка поля пароль через кнопку сброса")
    public void clearPasswordFieldButton() {
        click(RESET_FIELD_BUTTON);
        equalText(PASSWORD_FIELD, "enter password");
    }

    @Step("Переход на форму - Регистрация")
    public void openSignInForm() {
        click(REGISTERED_BUTTON);
    }

    @Step("Отображение ошибки валидации у поля - Пароль")
    public void checkValidatePasswordField() {
        waitForElement(PASSWORD_VALIDATE);
    }

    @Step("Отображение ошибки валидации у поля - Логин")
    public void checkValidateLoginField() {
        waitForElement(LOGIN_VALIDATE);
    }

}
