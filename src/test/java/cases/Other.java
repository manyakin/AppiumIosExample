package cases;

import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import settings.MobileSettings;

public class Other extends MobileSettings {

    @BeforeMethod(description = "Инициализация страниц")
    public void preparing() {
        objHome = new HomePage(driver);
        objLogIn = new LogInPage(driver);
    }

    @Feature("Прочее")
    @Test(description = "Проверка работы поиска")
    public void checkWorkSearchForm() {
        objHome.closeGuide()
               .openSearchForm()
               .viewSearchResult("Ios", "Mobile operating system by Apple");
    }

    @Feature("Прочее")
    @Test(description = "Проверка скрытия малой карточки информации")
    public void checkWorkHideMiniCard() {
        objHome.closeGuide()
               .hideHintCard();
    }

    @Feature("Прочее")
    @Test(description = "Проверка добавления списка для чтения")
    public void checkWorkAddReadingLists() {
        objHome.closeGuide()
               .openSavedForm()
               .openReadingForm()
               .addReadingLists(HomePage.Data.BOOK, HomePage.Data.DESCRIPTION)
               .checkCreateReadingList(HomePage.Data.BOOK, HomePage.Data.DESCRIPTION);
    }

    @Feature("Прочее")
    @Test(description = "Проверка корректного удаления списка для чтения")
    public void checkWorkRemoveReadingLists() throws InterruptedException {
        objHome.closeGuide()
                .openSavedForm()
                .openReadingForm()
                .addReadingLists(HomePage.Data.BOOK, HomePage.Data.DESCRIPTION)
                .deleteList(HomePage.Data.BOOK);
    }

    @Feature("Прочее")
    @Test(description = "Прохождение гайда после старта приложения")
    public void checkWorkStartGuide() {
        objHome.viewBasicGuide();
    }

}
