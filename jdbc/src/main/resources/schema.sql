DROP TABLE IF EXISTS book;
CREATE TABLE book
(
    id bigserial,
    name varchar,
    id_author bigint,
    id_genre bigint
);

DROP TABLE IF EXISTS author;
CREATE TABLE author
(
    id bigserial,
    name varchar
);

DROP TABLE IF EXISTS genre;
CREATE TABLE genre
(
    id bigserial,
    name varchar
);