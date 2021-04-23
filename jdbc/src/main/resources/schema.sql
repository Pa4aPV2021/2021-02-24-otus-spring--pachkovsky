DROP TABLE IF EXISTS book;
CREATE TABLE book
(
    id SERIAL,
    name character,
    id_author bigint,
    id_genre bigint
);

DROP TABLE IF EXISTS author;
CREATE TABLE author
(
    id SERIAL,
    name character
);

DROP TABLE IF EXISTS genre;
CREATE TABLE genre
(
    id SERIAL,
    name character
);