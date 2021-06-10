package ru.otus.spring.ajax.service;

import java.util.List;

import ru.otus.spring.ajax.domain.Author;

public interface AuthorService {
	List<Author> findAll();
}
