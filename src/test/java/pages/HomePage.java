package pages;

import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import settings.MobileMethods;

import static settings.MobileElements.*;

public class HomePage extends MobileMethods {
    public HomePage(IOSDriver driver) {super(driver);}

    private static final By GUIDE_SKIP = button("Skip");
    private static final By GUIDE_NEXT = button("Next");
    private static final By SAVED_BUTTON = button("Saved");
    private static final By READING_EDIT_BUTTON = button("Edit");
    private static final By SETTINGS_BUTTON = button("settings");
    private static final By SETTINGS_LOGIN_BUTTON = text("Log in");
    private static final By READING_BUTTON = button("Reading lists");
    private static final By READING_REMOVE_BUTTON = button("Delete");
    private static final By GUIDE_THIRD_PAGE_VALUE = text("English");
    private static final By FIRST_HINT_CARD_DISMISS = button("Dismiss");
    private static final By GUIDE_SECOND_PAGE_BODY = text("Explore feed");
    private static final By GUIDE_FOURTH_PAGE_CLOSE = button("Get started");
    private static final By SEARCH_RESULT = By.className("XCUIElementTypeWebView");
    private static final By CREATE_LIST_BUTTON = button("Create a new list");
    private static final By NEW_LIST_NAME_FIELD = field("reading list title");
    private static final By SEARCH_FIELD = By.className("XCUIElementTypeSearchField");
    private static final By GUIDE_PAGE = By.className("XCUIElementTypePageIndicator");
    private static final By NEW_LIST_SAVE_BUTTON = button("Create reading list");
    private static final By GUIDE_FOURTH_PAGE_REPORT = text("Send usage reports");
    private static final By GUIDE_SECOND_PAGE_HEADER = text("New ways to explore");
    private static final By GUIDE_FIRST_PAGE_HEADER = text("The free encyclopedia");
    private static final By NAVIGATE_RESULT_SEARCH_BUTTON = button("Search Wikipedia");
    private static final By GUIDE_FOURTH_PAGE_HEADER = text("Help make the app better");
    private static final By GUIDE_FIRST_PAGE_INFO = button("Learn more about Wikipedia");
    private static final By NEW_LIST_DESCRIPTION_FIELD = field("optional short description");
    private static final By GUIDE_THIRD_PAGE_HEADER = text("Search in nearly 300 languages");
    private static final By GUIDE_THIRD_PAGE_INFO = button("Add or edit preferred languages");
    private static final By GUIDE_FOURTH_PAGE_INFO = button("Learn more about data collected");
    private static final By FIRST_HINT_CARD_OPEN = button("Log in to sync your saved articles");
    private static final By GUIDE_THIRD_PAGE_BODY = textContains("found the following languages on your device");
    private static final By GUIDE_FOURTH_PAGE_BODY = text("Help improve the app by letting the Wikimedia Foundation know how you use it. Data collected is anonymous.");
    private static final By GUIDE_FIRST_PAGE_BODY = text("Wikipedia is written collaboratively by volunteers and consists of more than 40 million articles in nearly 300 languages.");

    public enum Data {
        BOOK("Book"),
        DESCRIPTION("Favorite");

        public String description;
        Data(String description) {
            this.description = description;
        }
        public String getDescription() {return description;}
    }

    @Step("Переход на форму авторизации")
    public HomePage openSearchForm() {
        click(SEARCH_FIELD);
        return this;
    }

    @Step("Скрытие гайда приложения")
    public HomePage closeGuide() {
        click(GUIDE_SKIP);
        return this;
    }

    @Step("Отображение результатов поиска")
    public void viewSearchResult(final String name, final String hint) {
        type(SEARCH_FIELD, name);
        click(link(hint));
        waitForElement(NAVIGATE_RESULT_SEARCH_BUTTON);
        waitForElement(SEARCH_RESULT);
    }

    @Step("Проверка скрытия малой карточки информации")
    public void hideHintCard() {
        click(FIRST_HINT_CARD_DISMISS);
        hide(FIRST_HINT_CARD_OPEN);
    }

    @Step("Переход на форму - Сохраненное")
    public HomePage openSavedForm() {
        click(SAVED_BUTTON);
        return this;
    }

    @Step("Переход на форму - Список для чтения")
    public HomePage openReadingForm() {
        click(READING_BUTTON);
        return this;
    }

    @Step("Добавление нового списка для чтения")
    public HomePage addReadingLists(final Data name, final Data description) {
        click(CREATE_LIST_BUTTON);
        waitForElement(NEW_LIST_NAME_FIELD);
        type(NEW_LIST_NAME_FIELD, name.description);
        type(NEW_LIST_DESCRIPTION_FIELD, description.description);
        click(NEW_LIST_SAVE_BUTTON);
        return this;
    }

    @Step("Проверка добавленного списка для чтения")
    public void checkCreateReadingList(final Data name, final Data description) {
        waitForElement(READING_BUTTON);
        check(link(name.description));
        check(link(description.description));
    }

    @Step("Удаление существующего списка для чтения")
    public void deleteList(final Data name) throws InterruptedException {
        Thread.sleep(2000); //Без принудительного ожидания не появляется кнпока "Удалить"
        click(READING_EDIT_BUTTON);
        click(link(name.description));
        click(READING_REMOVE_BUTTON);
        hide(link(name.description));
    }

    @Step("Переход на форму авторизации")
    public void openLogInForm() {
        click(SETTINGS_BUTTON);
        click(SETTINGS_LOGIN_BUTTON);
    }

    @Step("Отображение гайдов при старте приложения")
    public void viewBasicGuide() {
        waitForElement(GUIDE_FIRST_PAGE_HEADER);
        check(GUIDE_SKIP);
        check(GUIDE_FIRST_PAGE_BODY);
        check(GUIDE_FIRST_PAGE_INFO);
        equalText(GUIDE_PAGE, "page 1 of 4");

        click(GUIDE_NEXT);
        waitForElement(GUIDE_SECOND_PAGE_HEADER);
        check(GUIDE_SKIP);
        check(GUIDE_SECOND_PAGE_BODY);
        equalText(GUIDE_PAGE, "page 2 of 4");

        click(GUIDE_NEXT);
        waitForElement(GUIDE_THIRD_PAGE_HEADER);
        check(GUIDE_SKIP);
        check(GUIDE_THIRD_PAGE_BODY);
        check(GUIDE_THIRD_PAGE_VALUE);
        check(GUIDE_THIRD_PAGE_INFO);
        equalText(GUIDE_PAGE, "page 3 of 4");

        click(GUIDE_NEXT);
        waitForElement(GUIDE_FOURTH_PAGE_HEADER);
        check(GUIDE_FOURTH_PAGE_BODY);
        check(GUIDE_FOURTH_PAGE_INFO);
        check(GUIDE_FOURTH_PAGE_CLOSE);
        check(GUIDE_FOURTH_PAGE_REPORT);
    }

}
