package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

@Data
public class DataHelper {

    private DataHelper() {
    }

    public static User getUserInstBD() throws SQLException {
        val faker = new Faker();
        val runner = new QueryRunner();
        val dataSQL = "INSERT INTO users(id, login, password, status) VALUES (?, ?,?,?);";
        String id = faker.regexify("[0-9]{8}");
        String login = faker.name().username();
        String password = "$2a$10$np35JyeX.JHHRgPJjtWUY.XdbRV/zy9fBVXAUTNOfK27LfHgI/brq";
        String status = "active";
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            // обычная вставка
            runner.update(conn, dataSQL, id, login, password, status);
        }
        return new User(id, login, password, status);
    }


    public static User getInvalidUser() {
        Faker faker = new Faker(new Locale("ru"));
        String password = faker.regexify("[0-9]{8}");
        String login = faker.regexify("[a-z]{6}");
        String id = faker.regexify("[0-9]{8}");
        return new User(id, login, password, "active");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static String getVerificationCode (User user) throws SQLException {
        val runner = new QueryRunner();
        val dataSQL = "SELECT code FROM auth_codes WHERE user_id = ?";
        val conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        String id=user.getId();
        String code = runner.query(conn, dataSQL, new ScalarHandler<>(),id);
        return code;
    }

    public static VerificationCode getNotVerificationCodeFor() {
        Faker faker = new Faker(new Locale("ru"));
        String code = faker.regexify("[0-9]{10}");
        return new VerificationCode(code);
    }
}
