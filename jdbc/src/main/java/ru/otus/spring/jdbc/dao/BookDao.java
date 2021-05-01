package ru.otus.spring.jdbc.dao;

import java.util.List;

import ru.otus.spring.jdbc.domain.Book;

public interface BookDao {
	
	Book create(Book createdBook);

	void update(Book updatedBook);

	List<Book> findAll();

	void delete(Long id);

	Book findOne(Long id);

	int count();

}
