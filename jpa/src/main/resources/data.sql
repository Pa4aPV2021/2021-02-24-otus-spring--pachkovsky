/*insert author*/
insert into author (`name`) values ('L.N. Tolstoy');
insert into author (`name`) values ('J.G. Verne');
insert into author (`name`) values ('T.A. Dumas');
insert into author (`name`) values ('other');

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



