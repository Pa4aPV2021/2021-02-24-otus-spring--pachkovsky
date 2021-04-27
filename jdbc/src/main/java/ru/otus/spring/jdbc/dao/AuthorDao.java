package ru.otus.spring.jdbc.dao;

import java.util.List;

import ru.otus.spring.jdbc.domain.Author;

public interface AuthorDao {
	List<Author> findAll();
	Author findOne(Long id);
}
