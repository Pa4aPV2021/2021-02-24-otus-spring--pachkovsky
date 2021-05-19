package ru.otus.spring.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.jpa.dao.BookDao;
import ru.otus.spring.jpa.domain.Author;
import ru.otus.spring.jpa.domain.Book;
import ru.otus.spring.jpa.domain.Genre;

@Service
public class BookServiceImpl implements BookService {

	private final BookDao bookDaо;

	public BookServiceImpl(BookDao bookDaо) {
		this.bookDaо = bookDaо;
	}

	@Override
	public Book create(String name, Long id_author, Long id_genre) {
		return bookDaо.create(new Book(name, new Author(id_author), new Genre(id_genre)));
	}

	@Override
	public Book update(Long id, String name, Long idAuthor, Long idGenre) {
		Book oldBook = this.findOne(id);
		oldBook.setName(name);
		oldBook.setAuthor(new Author(idAuthor));
		oldBook.setGenre(new Genre(idGenre));
		bookDaо.update(oldBook);
		return oldBook;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Book> findAll() {
		return bookDaо.findAll();
	}

	@Transactional
	@Override
	public void delete(Long id) {
		bookDaо.delete(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Book findOne(Long id) {
		return bookDaо.findOne(id).orElseThrow(() -> new RuntimeException("book: " + id + " not found"));
	}

}
