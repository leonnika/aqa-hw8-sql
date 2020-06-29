package ru.netology.page;

import io.restassured.response.Response;
import ru.netology.data.Transfer;

import static io.restassured.RestAssured.given;
import static ru.netology.data.AuthTest.requestSpec;

public class TransferAPI {
    public void jsonPartTransfer(Transfer transfer, String token) {
        Response response =
                given() // "дано"
                        .spec(requestSpec) // указываем, какую спецификацию используем
                        .headers(
                                "Authorization",
                                "Bearer " + token)
                        .body(transfer) // передаём в теле объект, который будет преобразован в JSON
                        .when() // "когда"
                        .post("/api/transfer") // на какой путь, относительно BaseUri отправляем запрос
                        .then() // "тогда ожидаем"
                        .statusCode(200)
                        .extract()
                        .response();
    }
}
