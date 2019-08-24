package cases;

import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import pages.SignInPage;
import settings.MobileSettings;

public class SignIn extends MobileSettings {

    @BeforeMethod(description = "Инициализация страниц")
    public void preparing() {
        objHome = new HomePage(driver);
        objLogIn = new LogInPage(driver);
        objSignIn = new SignInPage(driver);
    }

    @Feature("Регистрация")
    @Test(description = "Отображение формы - Регистрация")
    public void checkViewSignInForm() {
        objHome.closeGuide()
               .openLogInForm();
        objLogIn.openSignInForm();
        objSignIn.viewSignInForm();
    }

    @Feature("Регистрация")
    @Test(description = "Проверка блокировки кнопки регистрации - Заблокирована")
    public void checkDisabledSignInButton() {
        objHome.closeGuide()
               .openLogInForm();
        objLogIn.openSignInForm();
        objSignIn.checkStatusSignInButton(SignInPage.Status.DISABLED);
    }

    @Feature("Регистрация")
    @Test(description = "Проверка блокировки кнопки регистрации - Разблокирована")
    public void checkEnabledSignInButton() {
        objHome.closeGuide()
               .openLogInForm();
        objLogIn.openSignInForm();
        objSignIn.fillForm(SignInPage.Credentials.ERROR)
                 .checkStatusSignInButton(SignInPage.Status.ENABLED);
    }

    @Feature("Регистрация")
    @Test(description = "Отображение ошибки валидации у поля - Логин")
    public void checkValidateIncorrectLogin() {
        objHome.closeGuide()
               .openLogInForm();
        objLogIn.openSignInForm();
        objSignIn.fillForm(SignInPage.Credentials.ERROR)
                 .saveForm()
                 .validatePhoneField();
    }

    @Feature("Регистрация")
    @Test(description = "Проверка перехода с формы регистрации на форму авторизации")
    public void checkJump() {
        objHome.closeGuide()
               .openLogInForm();
        objLogIn.openSignInForm();
        objSignIn.checkOpenAuth();
        objLogIn.viewLoginCard();
    }

    @Feature("Регистрация")
    @Test(description = "Очистка поля логина через кнопку сброса")
    public void checkClearPhoneField() {
        objHome.closeGuide()
               .openLogInForm();
        objLogIn.openSignInForm();
        objSignIn.clearLoginFieldButton();
    }

    @Feature("Регистрация")
    @Test(description = "Очистка поля пароль через кнопку сброса")
    public void checkClearPasswordField() {
        objHome.closeGuide()
               .openLogInForm();
        objLogIn.openSignInForm();
        objSignIn.clearPasswordFieldButton();
    }

    @Feature("Регистрация")
    @Test(description = "Очистка поля подтверждения пароля через кнопку сброса")
    public void checkClearPhoneField3() {
        objHome.closeGuide()
               .openLogInForm();
        objLogIn.openSignInForm();
        objSignIn.clearConfirmPasswordFieldButton();
    }

}
