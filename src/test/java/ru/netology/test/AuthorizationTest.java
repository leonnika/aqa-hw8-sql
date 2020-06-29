package ru.netology.test;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.User;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import java.sql.DriverManager;
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
        $(withText("Система заблокированна!")).waitUntil(visible, 150);
    }


    static void delData() throws SQLException {
        val delSQLtransfer = "DELETE FROM card_transactions;";
        val delSQLcard = "DELETE FROM cards;";
        val delSQLcode = "DELETE FROM auth_codes;";
        val delSQLuser = "DELETE FROM users;";
        val runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection("jdbc:mysql://192.168.99.100:3306/app", "app", "pass");
        ) {
           runner.update(conn, delSQLtransfer);
           runner.update(conn, delSQLcard);
           runner.update(conn, delSQLcode);
           runner.update(conn, delSQLuser);
        }
    }

}
