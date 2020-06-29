package ru.netology.page;

import ru.netology.data.UserCode;

import static io.restassured.RestAssured.given;
import static ru.netology.data.AuthTest.requestSpec;

public class VerificationAPI {

    public String jsonPartCode(UserCode userCode) {
        String token =
                given() // "дано"
                        .spec(requestSpec) // указываем, какую спецификацию используем
                        .body(userCode) // передаём в теле объект, который будет преобразован в JSON
                        .when() // "когда"
                        .post("/api/auth/verification") // на какой путь, относительно BaseUri отправляем запрос
                        .then() // "тогда ожидаем"
                        .statusCode(200)// код 200 OK
                        .extract()
                        .path("token");
        return token;
    }

}
