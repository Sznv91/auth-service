# ТЕСТОВОЕ ЗАДАНИЕ JAVA-РАЗРАБОТЧИКУ
Разработать REST-сервис для взаимодействия с клиентской частью мобильного приложения/web-сайта по HTTP/HTTPS протоколу. 
Для выполнения каждой операций взаимодействия мобильное приложение/web-сайт передает отдельный HTTP-запрос, содержащий токен доступа (access_token). На каждый запрос REST-сервис отвечает сообщением о результате операции.
Запросы и ответы формируются в кодировке UTF-8 и отправляются на URL REST-сервиса в теле HTTP запроса. Данные передаются в формате JSON.
Для формирования запроса к REST-сервису необходимо получить токен доступа. Авторизация осуществляется по протоколу OAuth 2.0, с использованием JWT-токенов. Для авторизации используются имя (clientname) и пароль клиента (clientpassword), согласованные предварительно. Обновление ключа доступа осуществляется по тому же адресу, что и авторизация.
REST-сервис выполняет 2 функции:
> регистрация пользователей 
  
> авторизация пользователей.

# РЕГИСТРАЦИЯ ПОЛЬЗОВАТЕЛЯ
При регистрации в мобильном приложении/web-сайте пользователь указывает имя, номер мобильного телефона, адрес электронной почты, пароль, повтор пароля, согласие с правилами, согласие на подписку.
>	Имя (name) – строка, только кириллица. Не менее 2-х символов без учета пробелов. Обязательное. Уникальное.
  
>	Номер мобильного телефона (phone) – 10 цифр, начиная с 9. Обязательное. Уникальное. Является логином пользователя.  

>	Адрес e-mail (email)– строка, формата xxx@yyy.zzz. Обязательное.  

>	Пароль (password) – строка, минимум 6 символов. Обязательное.  

>	Повтор пароля (password_replay) – строка, должен совпадать с паролем. Обязательное.  

>	Согласие с правилами (rules) - treu/false. Обязательное.  

>	Согласие на подписку (subscribe) – treu/false. Не обязательное.  

Проверка всех значений на стороне сервиса обязательна. Регистрация пользователя выполняется только после прохождения всех проверок. При успешной регистрации новому пользователю присваивается уникальный цифровой идентификатор (user_id).
После выполнения операции регистрации пользователя REST-сервис формирует ответ о результате выполнения операции (успех/ не успех). При неуспешном выполнении в ответ добавляется причина (код и текст ошибки). 
При регистрации пользователя используется токен доступа с типом  grandType client_credentials.

# АВТОРИЗАЦИЯ ПОЛЬЗОВАТЕЛЯ
Ранее зарегистрированные пользователи для доступа в мобильное приложение/web-сайт должны быть авторизованы.  
Авторизация пользователей осуществляется по протоколу OAuth 2.0. Для авторизации используются логин (phone) и пароль (password), указанные пользователем при регистрации. Используется токен доступа с типом grandTypes password.  
Сервис авторизации должен вернуть два токена access_token и refresh_token, имя и идентификатор пользователя в системе.  
Реализовать сервис обновления `access_token` используя `refresh_token`.  
  
##
  
##  
  
В проекте используется H2DB с реализацией хранения БД - InMemory.  
Сборка и запуск проекта не требует дополнительных настроек или ввода авторизационных данных.
Для демонстрации работы в таблицу `oauth_client_details` добавлены данные, которые можно использовать для проверки работоспособности.
Коллекции запросов postman находятся в корне проекта, в файле  
`Oauth2-service-rest.postman_collection`

## Register endpoint
~~~
curl --location --request POST "http://localhost:8080/oauth/user" 
--header "Content-Type: application/json; charset=utf-8" 
--data "{\"name\":\"Михиал\",
\"phone\":\"9111111111\",
\"email\":\"sazonov91@gmail.com\",
\"password\":\"Very_Secret_password123\",
\"confirmPassword\":\"Very_Secret_password123\",
\"rules\":\"true\",
\"subscribe\":\"false\"}"
~~~

## Generate accesstoken endpoint
~~~~
Generate Access Token:

curl --location --request POST "http://localhost:8080/oauth/token?username=9111111111&password=Very_Secret_password123&grant_type=password" 
--header "Authorization: Basic YXRvbTpTZWNyZXRfcGFzc3dvcmQxMjM="
~~~


## Validate endpoint
~~~
curl --location --request POST "http://localhost:8080/oauth/check_token?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTQxOTY0MTksInVzZXJfbmFtZSI6IjkyODc1MTgzMTkiLCJhdXRob3JpdGllcyI6WyJVc2VyIl0sImp0aSI6Ik1QTXFfRnhxQUF5LVNrVUdYR2ZJZDFEc1NrSSIsImNsaWVudF9pZCI6ImF0b20iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.-DxLN0FyUSU6L8kFYGpHkM4nSdWa8VwQ40De86c-ats" 
--header "Authorization: Basic YXRvbTpTZWNyZXRfcGFzc3dvcmQxMjM="
~~~

## Get Access token from Refresh token endpoint
~~~
curl --location --request POST "http://localhost:8080/oauth/token?grant_type=refresh_token&refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiI5Mjg3NTE4MzE5Iiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImF0aSI6Ik1QTXFfRnhxQUF5LVNrVUdYR2ZJZDFEc1NrSSIsImV4cCI6MTYxNDE5NzI1MiwiYXV0aG9yaXRpZXMiOlsiVXNlciJdLCJqdGkiOiJvX2FTbWFlTGlfTVJqb0JvRGZjTDd3bUZEU2ciLCJjbGllbnRfaWQiOiJhdG9tIn0.gwOhDsornHzAgaBtC2J81r6KQGLomdwymp9U5Ai4mpE" 
--header "Authorization: Basic YXRvbTpTZWNyZXRfcGFzc3dvcmQxMjM="
~~~

