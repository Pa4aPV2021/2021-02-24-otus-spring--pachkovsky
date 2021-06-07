package ru.otus.spring.thymeleaf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.thymeleaf.dao.GenreDao;
import ru.otus.spring.thymeleaf.domain.Genre;

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
