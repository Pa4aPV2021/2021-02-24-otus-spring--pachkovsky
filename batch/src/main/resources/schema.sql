







DROP TABLE persons IF EXISTS;

CREATE TABLE persons  (
    id bigserial,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    primary key (id)
);


DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id bigserial,
    name varchar,
    password varchar,
    primary key (id)
);

DROP TABLE IF EXISTS author;
CREATE TABLE author
(
    id bigserial,
    name varchar,
    primary key (id)
);

DROP TABLE IF EXISTS genre;
CREATE TABLE genre
(
    id bigserial,
    name varchar,
    primary key (id)
);


DROP TABLE IF EXISTS genre_entity_match;
CREATE TABLE genre_entity_match
(
    genre_entity_id bigint,
    genre_mongo_id varchar
);

DROP TABLE IF EXISTS author_entity_match;
CREATE TABLE author_entity_match
(
    author_entity_id bigint,
    author_mongo_id varchar
);

DROP TABLE IF EXISTS book;
CREATE TABLE book
(
    id bigserial,
    name varchar,
    id_author bigint REFERENCES author (id),
    id_genre bigint REFERENCES genre (id),
    primary key (id)
);

DROP TABLE IF EXISTS book_entity_match;
CREATE TABLE book_entity_match
(
    book_entity_id bigint,
    book_mongo_id varchar
);


DROP TABLE IF EXISTS comments;
CREATE TABLE comments
(
    id bigserial,
    text varchar,
    id_book bigint REFERENCES book (id) on delete cascade,
    primary key (id)
);