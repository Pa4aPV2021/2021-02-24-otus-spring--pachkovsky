DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id bigserial,
    name varchar,
    password varchar,
    role varchar,
    primary key (id)
);
