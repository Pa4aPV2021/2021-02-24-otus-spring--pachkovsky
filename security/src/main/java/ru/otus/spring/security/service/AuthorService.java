package ru.otus.spring.security.service;

import java.util.List;

import ru.otus.spring.security.domain.Author;

public interface AuthorService {
	List<Author> findAll();
}
