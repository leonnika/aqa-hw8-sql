package ru.netology;

import com.github.javafaker.Faker;
import static com.codeborne.selenide.Selenide.open;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.User;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbInteractionDbUtils {

    void init() throws SQLException {
        val runner = new QueryRunner();
        val dataSQLuser = "DELETE FROM users;";
        val dataSQLcard="DELETE FROM cards;";
        val dataSQLcode="DELETE FROM auth_codes;";
      //  val dataSQLcode="DELETE FROM auth_codes;";

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );

        ) {
            // обычная вставка
            runner.update(conn, dataSQLuser);
           runner.update(conn, dataSQLcard);
            runner.update(conn, dataSQLcode);
                   }
    }

    @BeforeEach
    void setUp() throws SQLException {
      //  init();
        val faker = new Faker();
        val runner = new QueryRunner();
        val dataSQL = "INSERT INTO users(id, login, password, status) VALUES (?, ?,?,?);";

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );

        ) {
            // обычная вставка
            runner.update(conn, dataSQL, 1,"asya", "$2a$10$np35JyeX.JHHRgPJjtWUY.XdbRV/zy9fBVXAUTNOfK27LfHgI/brq","active");
            runner.update(conn, dataSQL, 2,faker.name().username(), "$2a$10$np35JyeX.JHHRgPJjtWUY.XdbRV/zy9fBVXAUTNOfK27LfHgI/brq","active");
        }
    }

    @Test
    void stubTest() throws SQLException {
        val countSQL = "SELECT COUNT(*) FROM users;";
        val usersSQL = "SELECT * FROM users;";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            val count = runner.query(conn, countSQL, new ScalarHandler<>());
            System.out.println("1 "+count);
           // val first = runner.query(conn, usersSQL, new BeanHandler<>(User.class));
           // System.out.println(first);

         //   val all = runner.query(conn, usersSQL, new BeanListHandler<>(User.class));
          //  System.out.println(all);
        }
        open("http://localhost:9999");
    }

}