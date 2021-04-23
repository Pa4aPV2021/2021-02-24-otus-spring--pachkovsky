package ru.otus.spring.jdbc.dao;

import java.util.List;

import ru.otus.spring.jdbc.domain.Genre;

public interface GenreDao {
	Genre create(Genre createdGenre);

	Genre update(Genre updatedGenre);

	List<Genre> findAll();

	void delete(Long id);

	Genre findOne(Long id);

	int count();

}
