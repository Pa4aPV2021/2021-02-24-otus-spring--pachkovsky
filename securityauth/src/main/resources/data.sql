/*insert author*/
insert into author (`name`, `test`) values ('L.N. Tolstoy', 'test1');
insert into author (`name`, `test`) values ('J.G. Verne', 'test2' );
insert into author (`name`, `test`) values ('T.A. Dumas', 'test3');
insert into author (`name`, `test`) values ('other', 'test4');

/*insert genre*/
insert into genre (`name`) values ('novel');
insert into genre (`name`) values ('adventures');
insert into genre (`name`) values ('science fiction');
insert into genre (`name`) values ('other');

/*insert book*/
insert into book (name, id_author, id_genre) values ('War and Peace', 1, 1);
insert into book (name, id_author, id_genre) values ('Voyage au centre de la Terre', 2, 3);
insert into book (name, id_author, id_genre) values ('Les Enfants du capitaine Grant', 2, 2);
insert into book (name, id_author, id_genre) values ('Le Comte de Monte-Cristo', 3, 1);
insert into book (name, id_author, id_genre) values ('Les trois mousquetaires', 3, 1);

/*insert comments*/
insert into comments (text, id_book) values ('wow', 1);
insert into comments (text, id_book) values ('woooooow!!', 1);
insert into comments (text, id_book) values ('fie', 2);

/*insert users password:'password'*/
insert into users (name, password) values ('admin', '$2a$10$7209MBzUMyrEZywcmbpTDuxCvRHml7ScleAKBLgmPonJwec7xPjnS');		
insert into users (name, password) values ('user', '$2a$10$7209MBzUMyrEZywcmbpTDuxCvRHml7ScleAKBLgmPonJwec7xPjnS');	

/*insert PRIVILEGES*/
insert into PRIVILEGES (name) values ('READ_PRIVILEGE');		
insert into PRIVILEGES (name) values ('WRITE_PRIVILEGE');

/*insert ROLES*/
insert into ROLES (name) values ('ROLE_ADMIN');
insert into ROLES (name) values ('ROLE_USER');

/*insert ROLES_PRIVILEGES*/
insert into ROLES_PRIVILEGES (ROLE_ID, PRIVILEGE_ID) values (1, 1);
insert into ROLES_PRIVILEGES (ROLE_ID, PRIVILEGE_ID) values (1, 2);
insert into ROLES_PRIVILEGES (ROLE_ID, PRIVILEGE_ID) values (2, 1);

/*insert USERS_ROLES*/
insert into USERS_ROLES (USER_ID, ROLE_ID) values (1, 1);
insert into USERS_ROLES (USER_ID, ROLE_ID) values (2, 2);


