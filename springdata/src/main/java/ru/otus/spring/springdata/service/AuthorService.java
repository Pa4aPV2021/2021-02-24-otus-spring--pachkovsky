package ru.otus.spring.springdata.service;

import java.util.List;

import ru.otus.spring.springdata.domain.Author;

public interface AuthorService {
	List<Author> findAll();
}
