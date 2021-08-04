package ru.otus.spring.securityauth.service;

import java.util.List;

import ru.otus.spring.securityauth.domain.Book;

public interface BookService {

	Book update(Book updatedbook);

	List<Book> findAll();

	void deleteById(Long id);

	Book findById(Long id);

	Book create(Book book);


}
