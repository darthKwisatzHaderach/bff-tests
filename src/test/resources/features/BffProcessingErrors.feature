# language: ru
Функция: Обработка запроса на агрегацию информации о продукте

  Предыстория:
    Пусть есть внешние сервисы для корректной работы BFF

  Структура сценария:
    Допустим в случае ошибки на запрос "<Method>" "<Url pattern>" сервис возвращает код состояния <Service response code>
    Когда клиент выполняет запрос на агрегацию информации о продукте
    Тогда происходит обработка ошибки и формирование ответа с кодом состояния <Response code>, кодом <Code> и сообщением "<Message>"

    Примеры:
      | Method | Url pattern | Service response code | Response code | Code | Message                     |
      | POST   | INFO        | 500                   | 500           | 101  | ProductInfo service error.  |
      | POST   | PRICE       | 500                   | 500           | 102  | ProductPrice service error. |
      | POST   | STOCK       | 500                   | 500           | 103  | ProductStock service error. |
      | POST   | INFO        | 404                   | 404           | 107  | ProductInfo not found.      |
      | POST   | PRICE       | 404                   | 404           | 108  | ProductPrice not found.     |
      | POST   | STOCK       | 404                   | 404           | 109  | ProductStock not found.     |