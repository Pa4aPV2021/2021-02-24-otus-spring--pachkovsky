package ru.otus.spring.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.otus.spring.jpa.dao.AuthorDao;
import ru.otus.spring.jpa.domain.Author;

@Service
public class AuthorServiceImpl implements AuthorService {

	private final AuthorDao authorDao;

	public AuthorServiceImpl(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	@Override
	public List<Author> findAll() {
		return authorDao.findAll();
	}

}
