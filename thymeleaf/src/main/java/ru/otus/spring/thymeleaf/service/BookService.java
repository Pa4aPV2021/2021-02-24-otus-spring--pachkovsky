package ru.otus.spring.thymeleaf.service;

import java.util.List;

import ru.otus.spring.thymeleaf.domain.Book;

public interface BookService {

	Book update(Book updatedbook);

	List<Book> findAll();

	void deleteById(Long id);

	Book findById(Long id);

	Book create(Book book);


}
