package ru.otus.spring.securityauth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.securityauth.domain.Genre;
import ru.otus.spring.securityauth.repository.GenreDao;

@Service
public class GenreServiceImpl implements GenreService {

	private final GenreDao genreDao;

	public GenreServiceImpl(GenreDao genreDao) {
		this.genreDao = genreDao;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Genre> findAll() {
		return genreDao.findAll();
	}

}
