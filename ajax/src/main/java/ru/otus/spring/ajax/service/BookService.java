package ru.otus.spring.ajax.service;

import java.util.List;

import ru.otus.spring.ajax.domain.Book;

public interface BookService {

	Book update(Book updatedbook);

	List<Book> findAll();

	void deleteById(Long id);

	Book findById(Long id);

	Book create(Book book);


}
