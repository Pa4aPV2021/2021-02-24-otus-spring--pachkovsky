package ru.otus.spring.mongodb.service;

import java.util.List;

import ru.otus.spring.mongodb.domain.Book;

public interface BookService {

	Book update(String id, String name, String idAuthor, String idGenre);

	List<Book> findAll();

	void deleteById(String id);

	Book findById(String id);

	Book create(String name, String idAuthor, String idGenre);


}
