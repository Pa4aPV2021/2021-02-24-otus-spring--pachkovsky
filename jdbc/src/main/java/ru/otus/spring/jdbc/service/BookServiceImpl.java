package ru.otus.spring.jdbc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.otus.spring.jdbc.dao.BookDao;
import ru.otus.spring.jdbc.domain.Author;
import ru.otus.spring.jdbc.domain.Book;
import ru.otus.spring.jdbc.domain.Genre;

@Service
public class BookServiceImpl implements BookService {

	private final BookDao bookDao;

	public BookServiceImpl(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public Book create(String name, Long id_author, Long id_genre) {
		return bookDao.create(new Book(name, new Author(id_author), new Genre(id_genre)));
	}

	@Override
	public Book update(Long id, String name, Long id_author, Long id_genre) {
		Book oldBook = this.findOne(id);
		oldBook.setName(name);
		oldBook.setAuthor(new Author(id_author));
		oldBook.setGenre(new Genre(id_genre));
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

}
