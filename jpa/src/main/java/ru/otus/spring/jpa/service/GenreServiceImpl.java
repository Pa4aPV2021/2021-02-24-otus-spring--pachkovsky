package ru.otus.spring.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.otus.spring.jpa.dao.GenreDao;
import ru.otus.spring.jpa.domain.Genre;

@Service
public class GenreServiceImpl implements GenreService {

	private final GenreDao genreDao;

	public GenreServiceImpl(GenreDao genreDao) {
		this.genreDao = genreDao;
	}

	@Override
	public List<Genre> findAll() {
		return genreDao.findAll();
	}

}
