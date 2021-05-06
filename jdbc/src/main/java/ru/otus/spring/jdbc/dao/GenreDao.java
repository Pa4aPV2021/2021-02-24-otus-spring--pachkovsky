package ru.otus.spring.jdbc.dao;

import java.util.List;

import ru.otus.spring.jdbc.domain.Genre;

public interface GenreDao {
	List<Genre> findAll();
	Genre findOne(Long id);
}
