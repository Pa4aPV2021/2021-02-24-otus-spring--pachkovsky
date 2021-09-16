package ru.otus.spring.springdata.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.springdata.dao.AuthorDao;
import ru.otus.spring.springdata.domain.Author;

@Service
public class AuthorServiceImpl implements AuthorService {

	private final AuthorDao authorDao;

	public AuthorServiceImpl(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Author> findAll() {
		return authorDao.findAll();
	}

}
