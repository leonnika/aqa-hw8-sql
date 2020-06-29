# Тестирование функции авторизации приложения работающего с СУБД MySQL
## Краткое описание

Проведено позитивное тестирование фунцкции входа пользователя с использованием кода, полученного из БД.
При запуске приложения в БД записывается демо данные пользователей. Создан класс генерации и записи в БД новых пользователей. Пароль при записи в БД используется зашивровонный как у демо пользователей. При авторизации в таблицу кодов записываются сгенерированные приложением коды. Этот ког считывается из БД SQL запросом SELECT и используется для авторизации.
Так же была протестировано ситуации трехкратного неверного ввода пароля. 


## Руководство использования

* Для запуска контейнера MySQL выполните:

```
docker-compose up
```

* Для запуска БД выполните:
 
```
docker-compose exec mysql mysql -u app -p app

```


* Для запуска приложения выполните:

```
java -jar app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/app -P:jdbc.user=app -P:jdbc.password=pass
```

* Для запуска тестов:

```
./gradlew test -Dselenide.headless=true --info
```
В ходе тестирования найдена ошибка приложения:

[Система не блокируется при трехкратном неверном вводе пароля  ](https://github.com/leonnika/aqa-hw8-sql/issues/1)