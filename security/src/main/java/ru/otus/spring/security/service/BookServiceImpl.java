package ru.otus.spring.security.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.security.domain.Book;
import ru.otus.spring.security.repository.BookDao;

@Service
public class BookServiceImpl implements BookService {

	private final BookDao bookDaо;

	public BookServiceImpl(BookDao bookDaо) {
		this.bookDaо = bookDaо;
	}

	@Transactional
	@Override
	public Book create(Book Book) {
		return bookDaо.save(Book);
	}

	@Transactional
	@Override
	public Book update(Book updatedBook) {	
		return bookDaо.save(updatedBook);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Book> findAll() {
		return bookDaо.findAll();
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		bookDaо.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Book findById(Long id) {
		return bookDaо.findById(id).orElseThrow(() -> new NotFoundException());
	}

}
