package ru.otus.spring.jdbc.service;

import java.util.List;

import ru.otus.spring.jdbc.domain.Author;

public interface AuthorService {
	List<Author> findAll();
}
