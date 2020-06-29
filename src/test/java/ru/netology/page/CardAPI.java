package ru.netology.page;

import io.restassured.response.Response;
import ru.netology.data.User;

import static io.restassured.RestAssured.given;
import static ru.netology.data.AuthTest.requestSpec;


public class CardAPI {

    public void jsonPartListCard(String token){
        Response response =
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .headers(
                        "Authorization",
                        "Bearer " + token) // передаём token
                .when() // "когда"
                .get("/api/cards") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200)
                .extract()
                .response(); // код 200 OK

    }
}
