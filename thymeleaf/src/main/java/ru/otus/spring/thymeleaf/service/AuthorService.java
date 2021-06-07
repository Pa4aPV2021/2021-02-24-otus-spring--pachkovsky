package ru.otus.spring.thymeleaf.service;

import java.util.List;

import ru.otus.spring.thymeleaf.domain.Author;

public interface AuthorService {
	List<Author> findAll();
}
