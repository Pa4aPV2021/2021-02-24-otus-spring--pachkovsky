DROP TABLE IF EXISTS author;
CREATE TABLE author
(
    id bigserial,
    name varchar,
    test varchar,
    primary key (id)
);

DROP TABLE IF EXISTS genre;
CREATE TABLE genre
(
    id bigserial,
    name varchar,
    primary key (id)
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


DROP TABLE IF EXISTS comments;
CREATE TABLE comments
(
    id bigserial,
    text varchar,
    id_book bigint REFERENCES book (id) on delete cascade,
    primary key (id)
);
