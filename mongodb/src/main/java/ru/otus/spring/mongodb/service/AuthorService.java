package ru.otus.spring.mongodb.service;

import java.util.List;

import ru.otus.spring.mongodb.domain.Author;

public interface AuthorService {
	List<Author> findAll();
}
