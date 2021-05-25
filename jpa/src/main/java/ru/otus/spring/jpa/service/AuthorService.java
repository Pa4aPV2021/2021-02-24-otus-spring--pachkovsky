package ru.otus.spring.jpa.service;

import java.util.List;

import ru.otus.spring.jpa.domain.Author;

public interface AuthorService {
	List<Author> findAll();
}
