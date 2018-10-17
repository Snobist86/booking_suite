CREATE DATABASE hotel_booking ENCODING = 'UTF-8';

CREATE SCHEMA hotel_booking;

SET SEARCH_PATH TO hotel_booking;

CREATE TABLE hotel_booking.role (
  id   SERIAL PRIMARY KEY,
  name CHARACTER VARYING(32)
);

CREATE TABLE application_user (
  id       BIGSERIAL PRIMARY KEY,
  login    CHARACTER VARYING(64) UNIQUE NOT NULL,
  password CHARACTER VARYING(64)        NOT NULL,
  e_mail   CHARACTER VARYING(64)        NOT NULL,
  role_id  INTEGER REFERENCES hotel_booking.role (id) DEFAULT 2
);

CREATE TABLE hotel_booking.suite_size (
  id           BIGSERIAL PRIMARY KEY,
  name         CHARACTER VARYING(32),
  comment      CHARACTER VARYING(512),
  max_capacity INTEGER
);

CREATE TABLE hotel_booking.suite_category (
  id      BIGSERIAL PRIMARY KEY,
  name    CHARACTER VARYING(32),
  comment CHARACTER VARYING(512)
);

CREATE TABLE hotel_booking.suite (
  id                BIGSERIAL NOT NULL PRIMARY KEY,
  number            INTEGER   NOT NULL UNIQUE,
  suite_size_id     BIGINT    NOT NULL REFERENCES hotel_booking.suite_size (id),
  suite_category_id BIGINT    NOT NULL REFERENCES hotel_booking.suite_category (id),
  price             INTEGER   NOT NULL,
  floor             INTEGER   NOT NULL
);

CREATE TABLE hotel_booking.price (
  id                BIGSERIAL PRIMARY KEY,
  suite_size_id     BIGINT NOT NULL REFERENCES hotel_booking.suite_size (id),
  siute_category_id BIGINT NOT NULL REFERENCES hotel_booking.suite_category (id),
  price             INTEGER,
  UNIQUE (suite_size_id, siute_category_id)
);

CREATE TABLE hotel_booking.order_status (
  id   BIGSERIAL PRIMARY KEY,
  name CHARACTER VARYING(32)
);

CREATE TABLE hotel_booking.order (
  id               BIGSERIAL PRIMARY KEY,
  suite_id         BIGINT REFERENCES hotel_booking.suite (id),
  preview_order_id BIGINT NOT NULL REFERENCES hotel_booking.preview_order (id)
);

CREATE TABLE hotel_booking.preview_order (
  id                BIGSERIAL PRIMARY KEY,
  user_id           BIGINT NOT NULL REFERENCES hotel_booking.application_user (id),
  suite_size_id     INTEGER REFERENCES hotel_booking.suite_size (id),
  suite_category_id INTEGER REFERENCES hotel_booking.suite_category (id),
  order_status_id   BIGINT NOT NULL REFERENCES hotel_booking.order_status (id) DEFAULT 1,
  check_in_date     DATE,
  check_out_date    DATE,
  booking_date      DATE,
  comment           CHARACTER VARYING(256)
);

--------------------------------------------
INSERT INTO hotel_booking.order (suite_id, preview_order_id) VALUES (?, ?);

INSERT INTO hotel_booking.order_status (title) VALUES
  ('Забронировано. Ожидание обработки.'),
  ('Забронировано. Ожидание оплаты.'),
  ('Забронировано и оплачено.'),
  ('Отменено. Ожидание возврата оплаты.'),
  ('Отменено. Оплата возвращена.'),
  ('Отказано в регистрации.'),
  ('Отменено клиентом.');

INSERT INTO hotel_booking.suite_size (name, max_capacity, comment) VALUES
  ('одноместный', 1, 'Номер для одного человека.'),
  ('двухместный', 2, 'Номер для одного или двух человек.'),
  ('трехместный', 3, 'Номер от одного до трёх человек. Рекомендовано для семей с детьми.'),
  ('четырехместный', 4, 'Номер от одного до четырёх человек. Рекомендовано для семей с детьми.');

INSERT INTO hotel_booking.suite_category (name, comment) VALUES
  ('эконом', 'В номере есть две кровати, шкаф для одежы и чемоданов, холодильник, совмещенный санузел и душ. ' ||
             'Лучший выбор для бюджетного отдыха!'),
  ('комфорт', 'В двухкомнатном номере есть две кровати, шкаф для одежды, стол, диван, телевизор, холодильник, ' ||
              'совмещенный санузел, душ. Планировка номера составлена таким образом, чтобы Вы могли чувствовать ' ||
              'себя уютно.'),
  ('люкс', 'В двухкомнатном номере есть двуспальная кровать, стол, два диван, телевизор, холодильник, ' ||
           ' отдельная гардеробная, совмещенный санузел, ванна. Обстановка номера удовлетворит самого взыскательного' ||
           'клиента'),
  ('эксклюзив', 'Огромный номер. Обстановку можно подобрать из имеющихся наборов либо по вашему личному заказу.' ||
                'Бронируется минимум за 3 дня до прибытия. Указанная цена - минимальна и может быть изменена' ||
                'в зависимости от выбранного интерьра.');

INSERT INTO hotel_booking.suite (number, suite_size_id, suite_category_id, price, floor) VALUES
  (1, 2, 2, 300, 1),
  (2, 2, 2, 300, 1),
  (3, 2, 2, 300, 1),
  (4, 2, 2, 300, 1),
  (5, 2, 1, 150, 1),
  (6, 2, 1, 150, 1),
  (7, 2, 2, 300, 2),
  (8, 2, 2, 300, 2),
  (9, 2, 1, 150, 2),
  (10, 2, 1, 150, 2),
  (11, 2, 1, 150, 2),
  (12, 2, 1, 150, 2),
  (13, 2, 1, 150, 2),
  (14, 2, 2, 300, 2),
  (15, 2, 2, 300, 2),
  (16, 2, 2, 300, 2),
  (17, 2, 2, 300, 2),
  (18, 2, 2, 300, 2),
  (19, 2, 1, 150, 2),
  (20, 2, 1, 150, 2),
  (21, 4, 1, 450, 3),
  (22, 4, 1, 450, 3),
  (23, 3, 1, 300, 3),
  (24, 4, 1, 450, 3),
  (25, 2, 3, 750, 3),
  (26, 2, 3, 750, 3);

--------------------------------------------

SELECT *
FROM hotel_booking.order;