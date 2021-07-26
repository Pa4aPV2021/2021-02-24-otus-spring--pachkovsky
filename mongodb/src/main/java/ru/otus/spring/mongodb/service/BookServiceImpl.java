package ru.otus.spring.mongodb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.mongodb.dao.BookDao;
import ru.otus.spring.mongodb.domain.Author;
import ru.otus.spring.mongodb.domain.Book;
import ru.otus.spring.mongodb.domain.Genre;

@Service
public class BookServiceImpl implements BookService {

	private final BookDao bookDaо;

	public BookServiceImpl(BookDao bookDaо) {
		this.bookDaо = bookDaо;
	}

	@Transactional
	@Override
	public Book create(String name, String id_author, String id_genre) {
		return bookDaо.save(new Book(name, new Author(id_author), new Genre(id_genre)));
	}

	@Transactional
	@Override
	public Book update(String id, String name, String idAuthor, String idGenre) {
		Book oldBook = this.findById(id);
		oldBook.setName(name);
		oldBook.setAuthor(new Author(idAuthor));
		oldBook.setGenre(new Genre(idGenre));
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
		bookDaо.deleteById(id);;
	}

	@Transactional(readOnly = true)
	@Override
	public Book findById(String id) {
		return bookDaо.findById(id).orElseThrow(() -> new RuntimeException("book: " + id + " not found"));
	}

}
