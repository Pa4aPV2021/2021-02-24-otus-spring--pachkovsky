package ru.otus.spring.springdata.service;

import java.util.List;

import ru.otus.spring.springdata.domain.Genre;


public interface GenreService {
	List<Genre> findAll();
}
