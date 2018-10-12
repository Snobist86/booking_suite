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
  id      BIGSERIAL PRIMARY KEY,
  name    CHARACTER VARYING(32),
  comment CHARACTER VARYING(512)
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

CREATE TABLE hotel_suite_booking.request (
  id             BIGSERIAL PRIMARY KEY,
  size_id        BIGINT NOT NULL REFERENCES hotel_booking.suite_size (id),
  category_id    BIGINT NOT NULL REFERENCES hotel_booking.suite_category (id),
  check_in_date  DATE,
  check_out_date DATE,
  book_date      DATE,
  UNIQUE (suite_id, date)
);

DROP TABLE hotel_booking.order;

DELETE FROM hotel_booking.preview_order
WHERE id = 1;

ALTER TABLE hotel_booking.order_status
  ALTER COLUMN title TYPE CHARACTER VARYING(64);

ALTER TABLE hotel_booking.suite
  ADD CONSTRAINT some_name UNIQUE (id);

ALTER TABLE hotel_booking.preview_order
  ADD COLUMN total_price INTEGER;

ALTER TABLE hotel_booking.order
  DROP COLUMN user_id;

ALTER TABLE hotel_booking.order
  RENAME COLUMN finish_date TO check_out_date;

--------------------------------------------

INSERT INTO hotel_booking.order_status (title) VALUES
  ('Забронировано. Ожидание обработки.'),
  ('Забронировано. Ожидание оплаты.'),
  ('Забронировано и оплачено.'),
  ('Отменено. Ожидание возврата оплаты.'),
  ('Отменено. Оплата возвращена.'),
  ('Отказано в регистрации.');

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

INSERT INTO hotel_booking.order (user_id, suite_id, order_status_id, check_in_date,
                                 check_out_date, booking_date, comment) VALUES
  (1, 26, 3, '15-10-2018', '18-10-2018', '6-10-2018', 'sfs\ag\g');

INSERT INTO hotel_booking.suite (number, suite_size_id, suite_category_id, price, floor) VALUES
  (1, 2, 14, 300, 1),
  (2, 2, 14, 300, 1),
  (3, 2, 14, 300, 1),
  (4, 2, 14, 300, 1),
  (5, 2, 13, 150, 1),
  (6, 2, 13, 150, 1),
  (7, 2, 14, 300, 2),
  (8, 2, 14, 300, 2),
  (9, 2, 13, 150, 2),
  (10, 2, 13, 150, 2),
  (11, 2, 13, 150, 2),
  (12, 2, 13, 150, 2),
  (13, 2, 13, 150, 2),
  (14, 2, 14, 300, 2),
  (15, 2, 14, 300, 2),
  (16, 2, 14, 300, 2),
  (17, 2, 14, 300, 2),
  (18, 2, 14, 300, 2),
  (19, 2, 13, 150, 2),
  (20, 2, 13, 150, 2),
  (21, 4, 13, 450, 3),
  (22, 4, 13, 450, 3),
  (23, 3, 13, 300, 3),
  (24, 4, 13, 450, 3),
  (25, 2, 15, 750, 3),
  (26, 2, 15, 750, 3);

--------------------------------------------

UPDATE hotel_booking.order_status
SET title = 'Забронировано и оплачено.'
WHERE id = 3;

SELECT *
FROM hotel_booking.order_status;

SELECT *
FROM hotel_booking.preview_order;

SELECT
  st.id,
  st.suite_size_id,
  s.name,
  s.max_capacity,
  st.suite_category_id,
  c.name,
  st.price,
  st.floor
FROM hotel_booking.suite st
  INNER JOIN hotel_booking.suite_size s
    ON st.suite_size_id = s.id
  INNER JOIN hotel_booking.suite_category c
    ON st.suite_category_id = c.id;

SELECT
  s.id,
  s.number,
  s.suite_size_id,
  sz.name AS size_name,
  s.suite_category_id,
  c.name  AS category_name,
  s.price,
  s.floor
FROM hotel_booking.suite s
  INNER JOIN hotel_booking.suite_size sz
    ON sz.id = s.suite_size_id
  INNER JOIN hotel_booking.suite_category c
    ON c.id = s.suite_category_id
WHERE number = ?;


SELECT
  s.suite_size_id,
  sz.name    AS size_name,
  sz.comment AS size_comment,
  s.suite_category_id,
  c.name     AS category_name,
  c.comment  AS category_comment,
  s.price
FROM hotel_booking.suite s
  INNER JOIN hotel_booking.suite_size sz
    ON s.suite_size_id = sz.id
  INNER JOIN hotel_booking.suite_category c
    ON s.suite_category_id = c.id
WHERE (? IS NULL OR s.suite_size_id = ?) AND (? IS NULL OR s.suite_category_id = ?)
      AND (s.id NOT IN (SELECT o.suite_id
                        FROM hotel_booking.order o
                          INNER JOIN hotel_booking.preview_order po
                            ON po.id = o.preview_order_id
                        WHERE po.check_in_date BETWEEN ? AND ?))
      AND (s.id NOT IN (SELECT o.suite_id
                        FROM hotel_booking.order o
                          INNER JOIN hotel_booking.preview_order po
                            ON po.id = o.preview_order_id
                        WHERE po.check_out_date BETWEEN ? AND ?));

SELECT
  s.id,
  s.suite_size_id,
  sz.name,
  s.suite_category_id,
  c.name,
  s.price
FROM hotel_booking.suite s
  INNER JOIN hotel_booking.suite_size sz
    ON s.suite_size_id = sz.id
  INNER JOIN hotel_booking.suite_category c
    ON s.suite_category_id = c.id
WHERE s.suite_size_id = 2 AND s.suite_category_id = 14
      AND (s.id NOT IN (SELECT o.suite_id
                        FROM hotel_booking.order o
                          INNER JOIN hotel_booking.preview_order po
                            ON po.id = o.preview_order_id
                        WHERE po.check_in_date BETWEEN '2018-10-14' AND '2018-10-18'))
      AND (s.id NOT IN (SELECT o.suite_id
                        FROM hotel_booking.order o
                          INNER JOIN hotel_booking.preview_order po
                            ON po.id = o.preview_order_id
                        WHERE po.check_out_date BETWEEN '2018-10-14' AND '2018-10-18'));

SELECT o.suite_id
FROM hotel_booking.order o
  INNER JOIN hotel_booking.order_status os
    ON os.id = o.order_status_id
WHERE (o.check_in_date NOT BETWEEN '2018-10-20' AND '2018-10-21');

SELECT o.suite_id
FROM hotel_booking.order o
WHERE (o.order_status_id = 3) OR (o.check_out_date NOT BETWEEN '2018-10-16' AND '2018-10-18');

INSERT
INTO hotel_booking.preview_order (user_id, suite_size_id, suite_category_id,
                                  check_in_date, check_out_date, booking_date, total_price, comment)
VALUES (?, ?, ?, ?, ?, ?, ?, ?);

SELECT
  po.id,
  po.user_id,
  u.login,
  po.suite_size_id,
  ss.name AS size_name,
  po.suite_category_id,
  sc.name AS category_name,
  po.order_status_id,
  os.title,
  po.check_in_date,
  po.check_out_date,
  po.booking_date,
  po.comment,
  po.total_price
FROM hotel_booking.preview_order po
  INNER JOIN hotel_booking.application_user u
    ON po.user_id = u.id
  INNER JOIN hotel_booking.order_status os
    ON po.order_status_id = os.id
  INNER JOIN hotel_booking.suite_size ss
  ON po.suite_size_id = ss.id
  INNER JOIN hotel_booking.suite_category sc
  ON po.suite_category_id = sc.id
WHERE order_status_id = 1;