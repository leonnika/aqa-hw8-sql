package ru.netology.page;

import com.github.javafaker.Faker;
import lombok.val;
import org.openqa.selenium.Keys;
import ru.netology.data.User;

import java.sql.SQLException;

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
}
