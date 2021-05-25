package ru.otus.spring.jpa.service;

import java.util.List;

import ru.otus.spring.jpa.domain.Book;

public interface BookService {

	Book update(Long id, String name, Long idAuthor, Long idGenre);

	List<Book> findAll();

	void delete(Long id);

	Book findOne(Long id);

	Book create(String name, Long idAuthor, Long idGenre);


}
