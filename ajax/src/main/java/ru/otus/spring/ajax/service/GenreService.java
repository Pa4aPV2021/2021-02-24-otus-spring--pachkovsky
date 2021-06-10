package ru.otus.spring.ajax.service;

import java.util.List;

import ru.otus.spring.ajax.domain.Genre;


public interface GenreService {
	List<Genre> findAll();
}
