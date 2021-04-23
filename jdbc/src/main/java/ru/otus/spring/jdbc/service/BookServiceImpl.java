package ru.otus.spring.jdbc.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import ru.otus.spring.jdbc.dao.AuthorDao;
import ru.otus.spring.jdbc.dao.BookDao;
import ru.otus.spring.jdbc.dao.GenreDao;
import ru.otus.spring.jdbc.domain.Book;

@Service
public class BookServiceImpl implements BookService {

	private final BookDao bookDao;
	private final GenreDao genreDao;
	private final AuthorDao authorDao;

	public BookServiceImpl(BookDao bookDao, GenreDao genreDao, AuthorDao authorDao) {
		this.bookDao = bookDao;
		this.genreDao = genreDao;
		this.authorDao = authorDao;
	}

	@Override
	public Book create(String name, Long id_author, Long id_genre) {
		return bookDao.create(new Book(name, authorDao.findOne(id_author), genreDao.findOne(id_genre)));
	}

	@Override
	public Book update(Long id, String name, Long id_author, Long id_genre) {
		Book oldBook = this.findOne(id);
		BeanUtils.copyProperties(new Book(id, name, authorDao.findOne(id_author), genreDao.findOne(id_genre)), oldBook,
				"id");
		bookDao.update(oldBook);
		return oldBook;
	}

	@Override
	public List<Book> findAll() {
		return bookDao.findAll();
	}

	@Override
	public void delete(Long id) {
		bookDao.delete(id);
	}

	@Override
	public Book findOne(Long id) {
		return bookDao.findOne(id);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
