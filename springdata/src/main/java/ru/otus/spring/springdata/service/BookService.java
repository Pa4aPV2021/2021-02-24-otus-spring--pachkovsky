package ru.otus.spring.springdata.service;

import java.util.List;

import ru.otus.spring.springdata.domain.Book;

public interface BookService {

	Book update(Long id, String name, Long idAuthor, Long idGenre);

	List<Book> findAll();

	void deleteById(Long id);

	Book findById(Long id);

	Book create(String name, Long idAuthor, Long idGenre);


}
