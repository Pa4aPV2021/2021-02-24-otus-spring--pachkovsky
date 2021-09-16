package ru.otus.spring.jpa.dao;

import java.util.List;
import java.util.Optional;

import ru.otus.spring.jpa.domain.Author;


public interface AuthorDao {
	List<Author> findAll();
	Optional<Author> findOne(Long id);
}
