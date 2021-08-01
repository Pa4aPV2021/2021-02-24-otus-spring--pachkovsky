package ru.otus.spring.mongodb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.mongodb.dao.AuthorDao;
import ru.otus.spring.mongodb.dao.BookDao;
import ru.otus.spring.mongodb.dao.GenreDao;
import ru.otus.spring.mongodb.domain.Author;
import ru.otus.spring.mongodb.domain.Book;
import ru.otus.spring.mongodb.domain.Genre;

@Service
public class BookServiceImpl implements BookService {

	private final BookDao bookDaо;
	private final AuthorDao authorDao;
	private final GenreDao genreDao;

	public BookServiceImpl(BookDao bookDaо, AuthorDao authorDao, GenreDao genreDao) {
		this.bookDaо = bookDaо;
		this.authorDao = authorDao;
		this.genreDao = genreDao;
	}

	@Transactional
	@Override
	public Book create(String name, String idAuthor, String idGenre) {

		Author findedAuthor = authorDao.findById(idAuthor)
				.orElseThrow(() -> new RuntimeException("author: " + idAuthor + " not found"));

		Genre findedGenre = genreDao.findById(idGenre)
				.orElseThrow(() -> new RuntimeException("genre: " + idGenre + " not found"));

		return bookDaо.save(new Book(name, findedAuthor, findedGenre));
	}

	@Transactional
	@Override
	public Book update(String id, String name, String idAuthor, String idGenre) {
		Book oldBook = this.findById(id);

		if (!oldBook.getName().equals(name)) {
			oldBook.setName(name);
		}

		if (!oldBook.getAuthor().getId().equals(idAuthor)) {
			Author findedAuthor = authorDao.findById(idAuthor)
					.orElseThrow(() -> new RuntimeException("author: " + idAuthor + " not found"));
			oldBook.setAuthor(findedAuthor);
		}

		if (!oldBook.getGenre().getId().equals(idGenre)) {
			Genre findedGenre = genreDao.findById(idGenre)
					.orElseThrow(() -> new RuntimeException("genre: " + idGenre + " not found"));
			oldBook.setGenre(findedGenre);
		}

		bookDaо.save(oldBook);
		return oldBook;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Book> findAll() {
		return bookDaо.findAll();
	}

	@Transactional
	@Override
	public void deleteById(String id) {
		bookDaо.deleteById(id);
		;
	}

	@Transactional(readOnly = true)
	@Override
	public Book findById(String id) {
		return bookDaо.findById(id).orElseThrow(() -> new RuntimeException("book: " + id + " not found"));
	}

}
