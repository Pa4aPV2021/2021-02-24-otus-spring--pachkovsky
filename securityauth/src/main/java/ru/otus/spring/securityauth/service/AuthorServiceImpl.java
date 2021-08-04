package ru.otus.spring.securityauth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.securityauth.domain.Author;
import ru.otus.spring.securityauth.repository.AuthorDao;

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
