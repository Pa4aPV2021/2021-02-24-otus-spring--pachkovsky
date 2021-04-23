package ru.otus.spring.jdbc.dao;

import java.util.List;

import ru.otus.spring.jdbc.domain.Author;

public interface AuthorDao {
	Author create(Author createdAuthor);

	Author update(Author updatedAuthor);

	List<Author> findAll();

	void delete(Long id);

	Author findOne(Long id);

	int count();

}
