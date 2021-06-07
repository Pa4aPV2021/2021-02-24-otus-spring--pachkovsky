package ru.otus.spring.thymeleaf.service;

import java.util.List;

import ru.otus.spring.thymeleaf.domain.Genre;


public interface GenreService {
	List<Genre> findAll();
}
