# language: ru
Функция: Обработка запроса на агрегацию информации о продукте

  Предыстория:
    Пусть есть внешние сервисы для корректной работы BFF

  Структура сценария: Обработка ошибок от сервиса
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

  Структура сценария: Обработка разрыва соединения с сервисом
    Допустим при запросе "<Method>" "<Url pattern>" происходит разрыв соединения
    Когда клиент выполняет запрос на агрегацию информации о продукте
    Тогда происходит обработка ошибки и формирование ответа с кодом состояния 500, кодом <Code> и сообщением "<Message>"

    Примеры:
      | Method | Url pattern | Code | Message                                                              |
      | POST   | INFO        | 101  | ProductInfo service error: HTTP/1.1 header parser received no bytes  |
      | POST   | PRICE       | 102  | ProductPrice service error: HTTP/1.1 header parser received no bytes |
      | POST   | STOCK       | 103  | ProductStock service error: HTTP/1.1 header parser received no bytes |

  Структура сценария: Обработка некорректного ответа от сервиса
    Допустим при запросе "<Method>" "<Url pattern>" сервис возвращает код состояния 200, но некорректный ответ
    Когда клиент выполняет запрос на агрегацию информации о продукте
    Тогда происходит обработка ошибки и формирование ответа с кодом состояния 500, кодом <Code> и сообщением "<Message>"

    Примеры:
      | Method | Url pattern | Code | Message                           |
      | POST   | INFO        | 104  | ProductInfo unexpected response.  |
      | POST   | PRICE       | 105  | ProductPrice unexpected response. |
      | POST   | STOCK       | 106  | ProductStock unexpected response. |