package ru.otus.spring.securityauth.service;

import java.util.List;

import ru.otus.spring.securityauth.domain.Genre;


public interface GenreService {
	List<Genre> findAll();
}
