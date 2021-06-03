# language: ru
Функция: Обработка запроса на агрегацию информации о продукте

  Сценарий: Обработка запроса на агрегацию информации о продукте (источник "OFFLINE")
    Пусть есть внешние сервисы для корректной работы BFF
    Когда клиент выполняет запрос на агрегацию информации о продукте для источника "OFFLINE"
    Тогда BFF возвращает ответ с полным набором полей

  Сценарий: Обработка запроса на агрегацию информации о продукте (источник "DESKTOP")
    Пусть есть внешние сервисы для корректной работы BFF
    Когда клиент выполняет запрос на агрегацию информации о продукте для источника "DESKTOP"
    Тогда BFF возвращает ответ со стандартным набором полей

  Сценарий: Обработка запроса на агрегацию информации о продукте (источник "MOBILE")
    Пусть есть внешние сервисы для корректной работы BFF
    Когда клиент выполняет запрос на агрегацию информации о продукте для источника "MOBILE"
    Тогда BFF возвращает ответ с минимальным набором полей