package ru.otus.spring.security.service;

import java.util.List;

import ru.otus.spring.security.domain.Genre;


public interface GenreService {
	List<Genre> findAll();
}
