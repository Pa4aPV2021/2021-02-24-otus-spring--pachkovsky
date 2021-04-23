package ru.otus.spring.jdbc.service;

import java.util.List;

import ru.otus.spring.jdbc.domain.Book;

public interface BookService {

	int count();

	Book update(Long id, String name, Long id_author, Long id_genre);

	List<Book> findAll();

	void delete(Long id);

	Book findOne(Long id);

	Book create(String name, Long id_author, Long id_genre);

}
