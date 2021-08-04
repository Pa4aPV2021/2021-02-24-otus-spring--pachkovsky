package ru.otus.spring.securityauth.service;

import java.util.List;

import ru.otus.spring.securityauth.domain.Author;

public interface AuthorService {
	List<Author> findAll();
}
