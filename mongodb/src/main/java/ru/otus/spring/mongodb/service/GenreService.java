package ru.otus.spring.mongodb.service;

import java.util.List;

import ru.otus.spring.mongodb.domain.Genre;


public interface GenreService {
	List<Genre> findAll();
}
