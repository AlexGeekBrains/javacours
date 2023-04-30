CREATE SCHEMA `cinema`;
CREATE TABLE movies (
                        movie_id INT AUTO_INCREMENT PRIMARY KEY,
                        movie_title VARCHAR(255) NOT NULL,
                        duration INT NOT NULL
);

CREATE TABLE seances (
                         seance_id INT AUTO_INCREMENT PRIMARY KEY,
                         movie_id INT NOT NULL,
                         start_time DATETIME NOT NULL,
                         ticket_price DECIMAL(5,2) NOT NULL,
                         FOREIGN KEY (movie_id) REFERENCES movies(movie_id)
);

CREATE TABLE tickets (
                         ticket_id INT AUTO_INCREMENT PRIMARY KEY,
                         seance_id INT NOT NULL,
                         FOREIGN KEY (seance_id) REFERENCES seances(seance_id)
);

INSERT INTO movies (movie_title, duration)
VALUES
    ('The Shawshank Redemption', 142),
    ('The Godfather', 175),
    ('The Dark Knight', 152),
    ('Schindler''s List', 195),
    ('Forrest Gump', 142);

INSERT INTO seances (movie_id, start_time, ticket_price)
VALUES
    (1, '2023-04-29 17:00:00', 10.99),
    (1, '2023-04-29 20:00:00', 12.99),
    (1, '2023-04-29 22:30:00', 13.99),
    (2, '2023-04-29 14:00:00', 9.99),
    (2, '2023-04-29 19:00:00', 8.99),
    (3, '2023-04-29 16:00:00', 11.99),
    (3, '2023-04-29 19:30:00', 13.99),
    (4, '2023-04-29 12:30:00', 7.99),
    (4, '2023-04-29 15:30:00', 8.99),
    (5, '2023-04-29 20:30:00', 9.99);

INSERT INTO tickets (seance_id)
VALUES
    (1),
    (2),
    (3),
    (4),
    (5),
    (6),
    (7),
    (8),
    (9),
    (10);

-- Задача: ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
-- Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;

SELECT s1.seance_id as id1, m1.movie_title as "Название фильма1", s1.start_time as "Начало фильма1", m1.duration as "Длительность фильма1",
       s2.seance_id as id2, m2.movie_title as "Название фильма2", s2.start_time as "Начало фильма2", m2.duration as "Длительность фильма2"
FROM seances s1
         INNER JOIN movies m1 ON s1.movie_id = m1.movie_id
         INNER JOIN seances s2 ON s1.start_time < s2.start_time AND s1.start_time + INTERVAL m1.duration MINUTE > s2.start_time
    INNER JOIN movies m2 ON s2.movie_id = m2.movie_id
ORDER BY s1.start_time ASC;

-- Задача: перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
-- Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;

SELECT m1.movie_title AS "фильм 1",
       s1.start_time AS "время начала",
       SEC_TO_TIME(m1.duration*60) AS "длительность",
       s2.start_time AS "время начала второго фильма",
       TIMEDIFF(s2.start_time, ADDTIME(s1.start_time, SEC_TO_TIME(m1.duration*60))) AS "длительность перерыва"
FROM seances s1
         JOIN movies m1 ON s1.movie_id = m1.movie_id
         LEFT JOIN seances s2 ON s2.start_time = (SELECT MIN(start_time)
                                                  FROM seances
                                                  WHERE start_time > ADDTIME(s1.start_time, SEC_TO_TIME(m1.duration*60)))
WHERE TIMEDIFF(s2.start_time, ADDTIME(s1.start_time, SEC_TO_TIME(m1.duration*60))) >= '00:30:00'
ORDER BY TIMEDIFF(s2.start_time, ADDTIME(s1.start_time, SEC_TO_TIME(m1.duration*60))) DESC;


-- список фильмов, для каждого — с указанием общего числа посетителей за все время,
-- среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
-- Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
SELECT
    COALESCE(m.movie_title, 'Итого') AS "Название фильма",
    COUNT(t.ticket_id) AS "Число посетителей",
    AVG((SELECT COUNT(*) FROM tickets WHERE seance_id = s.seance_id)) AS "Среднее число зрителей за сеанс",
    SUM(s.ticket_price * (SELECT COUNT(*) FROM tickets WHERE seance_id = s.seance_id)) AS "Сумма сборов"
FROM seances s
         LEFT JOIN movies m ON s.movie_id = m.movie_id
         LEFT JOIN tickets t ON s.seance_id = t.seance_id
GROUP BY m.movie_title WITH ROLLUP
ORDER BY SUM(s.ticket_price * (SELECT COUNT(*) FROM tickets WHERE seance_id = s.seance_id)) DESC;


-- Задача: число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
-- с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).

SELECT
    CASE
        WHEN HOUR(start_time) BETWEEN 9 AND 15 THEN '9-15'
    WHEN HOUR(start_time) BETWEEN 15 AND 18 THEN '15-18'
    WHEN HOUR(start_time) BETWEEN 18 AND 21 THEN '18-21'
    ELSE '21-00'
END AS "временной интервал",
  COUNT(*) AS "посетителей",
  SUM(seances.ticket_price) AS "доход"
FROM tickets
JOIN seances ON tickets.seance_id = seances.seance_id
GROUP BY 1;
