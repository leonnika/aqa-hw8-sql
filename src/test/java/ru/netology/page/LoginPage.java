package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.val;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;
import ru.netology.data.User;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public VerificationPage validLogin(User user) throws SQLException {
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue("qwerty123");
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }

    public void invalidThreePassword(User user) throws SQLException {
        val faker = new Faker();
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(faker.regexify("[a-z]{6}"));
        $("[data-test-id=action-login]").click();
        $("[data-test-id=password] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=password] input").sendKeys(Keys.DELETE);
        $("[data-test-id=password] input").setValue(faker.regexify("[a-z]{6}"));
        $("[data-test-id=action-login]").click();
        $("[data-test-id=password] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=password] input").sendKeys(Keys.DELETE);
        $("[data-test-id=password] input").setValue(faker.regexify("[a-z]{6}"));
        $("[data-test-id=action-login]").click();
    }

    public void loginEmpty(User user) {
        $("[data-test-id=password] input").setValue(user.getLogin());
        $("[data-test-id=action-login]").click();
        SelenideElement login = $("[data-test-id=login]");
        login.$("[class='input__sub']").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    public void passwordEmpty(User user) {
        $("[data-test-id=login] input").setValue("qwerty123");
        $("[data-test-id=action-login]").click();
        SelenideElement password = $("[data-test-id=password]");
        password.$("[class='input__sub']").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    public  void invalidLogin(User user) throws SQLException {
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Система заблокированна!")).waitUntil(visible, 150);
    }
    }
