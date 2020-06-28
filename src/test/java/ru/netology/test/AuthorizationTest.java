package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.User;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationTest {
    LoginPage loginPage;
    User user;
    VerificationPage verificationPage;

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
    }

    @Test
    void shouldAuthorizationValidUserValidCode() throws SQLException {
        loginPage = new LoginPage();
        user = DataHelper.getUserInstBD();
        verificationPage = loginPage.validLogin(user);
        verificationPage.validCodeVerify(user);
    }

    @Test
    void shouldNotAuthorizationInvalidPassword() throws SQLException {
        loginPage = new LoginPage();
        user = DataHelper.getUserInstBD();
        loginPage.invalidThreePassword(user);
        $(withText("Система заблокированна!")).waitUntil(visible, 15000);
    }
}
