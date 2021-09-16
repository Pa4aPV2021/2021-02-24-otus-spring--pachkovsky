package ru.otus.spring.jpa.service;

import java.util.List;

import ru.otus.spring.jpa.domain.Genre;


public interface GenreService {
	List<Genre> findAll();
}
