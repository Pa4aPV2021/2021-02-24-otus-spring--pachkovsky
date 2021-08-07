DROP TABLE persons IF EXISTS;

CREATE TABLE persons  (
    id bigserial,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    primary key (id)
);