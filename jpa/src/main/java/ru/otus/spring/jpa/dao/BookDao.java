package ru.otus.spring.jpa.dao;

import java.util.List;
import java.util.Optional;

import ru.otus.spring.jpa.domain.Book;

public interface BookDao {

	Book create(Book createdBook);

	Book update(Book updatedBook);

	List<Book> findAll();

	void delete(Long id);

	Optional<Book> findOne(Long id);

}
