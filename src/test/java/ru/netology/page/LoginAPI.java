package ru.netology.page;

import ru.netology.data.UserAPI;

import static io.restassured.RestAssured.given;
import static ru.netology.data.AuthTest.requestSpec;

public class LoginAPI {

    public void jsonPartLogin(UserAPI user) {
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/auth") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

}
