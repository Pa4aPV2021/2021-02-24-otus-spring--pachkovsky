package ru.otus.spring.jpa.dao;

import java.util.List;
import java.util.Optional;

import ru.otus.spring.jpa.domain.Genre;

public interface GenreDao {
	List<Genre> findAll();

	Optional<Genre> findOne(Long id);
}
