package ru.netology.test;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.*;
import ru.netology.page.*;

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
    void shouldNotAuthorizationValidUserInValidCode() throws SQLException {
        loginPage = new LoginPage();
        user = DataHelper.getUserInstBD();
        verificationPage = loginPage.validLogin(user);
        verificationPage.validCodeVerify(user);
    }

    @Test
    void shouldNotAuthorizationInvalidUser() throws SQLException {
        loginPage = new LoginPage();
        user = DataHelper.getInvalidUser();
        loginPage.invalidLogin(user);
        }

    @Test
    void shouldNotAuthorizationPasswordEmpty() throws SQLException {
        loginPage = new LoginPage();
        user = DataHelper.getUserInstBD();
        loginPage.passwordEmpty(user);
    }

    @Test
    void shouldNotAuthorizationloginEmpty() throws SQLException {
        loginPage = new LoginPage();
        user = DataHelper.getUserInstBD();
        loginPage.loginEmpty(user);
    }

    @Test
    void shouldNotAuthorizationValidUserInvalidCode() throws SQLException {
        loginPage = new LoginPage();
        user = DataHelper.getUserInstBD();
        verificationPage = loginPage.validLogin(user);
        verificationPage.invalidCodeVerify();
    }

    @Test
    void shouldNotAuthorizationValidUserCodeEmpty() throws SQLException {
        loginPage = new LoginPage();
        user = DataHelper.getUserInstBD();
        verificationPage = loginPage.validLogin(user);
        verificationPage.EmptyCodeVerify();
    }

    @AfterAll
    public static void delData() throws SQLException {
        val delSQLtransfer = "DELETE FROM card_transactions;";
        val delSQLcard = "DELETE FROM cards;";
        val delSQLcode = "DELETE FROM auth_codes;";
        val delSQLuser = "DELETE FROM users;";
        val runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            runner.update(conn, delSQLtransfer);
            runner.update(conn, delSQLcard);
            runner.update(conn, delSQLcode);
            runner.update(conn, delSQLuser);
        }
    }
}


