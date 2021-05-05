package ru.otus.spring.jdbc.service;

import java.util.List;

import ru.otus.spring.jdbc.domain.Genre;

public interface GenreService {
	List<Genre> findAll();
}
