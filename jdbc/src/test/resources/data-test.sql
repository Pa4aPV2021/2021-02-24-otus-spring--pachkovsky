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

