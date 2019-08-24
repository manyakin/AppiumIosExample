package cases;

import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import settings.MobileSettings;

import static pages.LogInPage.Password.CORRECT;
import static pages.LogInPage.Password.INCORRECT;

public class LogIn extends MobileSettings {

    @BeforeMethod(description = "Инициализация страниц")
    public void preparing() {
        objHome = new HomePage(driver);
        objLogIn = new LogInPage(driver);
    }

    @Feature("Авторизация")
    @Test(description = "Отображение формы - Авторизация")
    public void checkViewLogInForm() {
        objHome.closeGuide()
               .openLogInForm();
        objLogIn.viewLoginCard();
    }

    @Feature("Авторизация")
    @Test(description = "Проверка блокировки кнопки входа - Заблокирована")
    public void checkDisabledLogInButton() {
        objHome.closeGuide()
               .openLogInForm();
        objLogIn.checkStatusLogInButton(LogInPage.Status.DISABLED);
    }

    @Feature("Авторизация")
    @Test(description = "Проверка блокировки кнопки входа - Разблокирована")
    public void checkEnabledLogInButton() {
        objHome.closeGuide()
               .openLogInForm();
        objLogIn.fillLogIn(LogInPage.Credentials.CORRECT, CORRECT)
                .checkStatusLogInButton(LogInPage.Status.ENABLED);
    }

    @Feature("Авторизация")
    @Test(description = "Отображение ошибки валидации у поля - Пароль")
    public void checkValidateIncorrectPassword() {
        objHome.closeGuide()
               .openLogInForm();
        objLogIn.fillLogIn(LogInPage.Credentials.CORRECT, INCORRECT)
                .saveForm()
                .checkValidatePasswordField();
    }

    @Feature("Авторизация")
    @Test(description = "Отображение ошибки валидации у поля - Логин")
    public void checkValidateIncorrectLogin() {
        objHome.closeGuide()
               .openLogInForm();
        objLogIn.fillLogIn(LogInPage.Credentials.ERROR, CORRECT)
                .saveForm()
                .checkValidateLoginField();
    }

    @Feature("Авторизация")
    @Test(description = "Очистка поля логина через кнопку сброса")
    public void checkClearLoginField() {
        objHome.closeGuide()
               .openLogInForm();
        objLogIn.fillLogIn(LogInPage.Credentials.CORRECT, CORRECT)
                .clearLoginFieldButton();
    }

    @Feature("Авторизация")
    @Test(description = "Очистка поля пароля через кнопку сброса")
    public void checkClearPasswordField() {
        objHome.closeGuide()
               .openLogInForm();
        objLogIn.fillLogIn(LogInPage.Credentials.CORRECT, CORRECT)
                .clearPasswordFieldButton();
    }

}
