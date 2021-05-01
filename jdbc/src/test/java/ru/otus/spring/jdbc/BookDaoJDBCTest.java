package ru.otus.spring.jdbc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import ru.otus.spring.jdbc.dao.AuthorDaoJDBC;
import ru.otus.spring.jdbc.dao.BookDaoJDBC;
import ru.otus.spring.jdbc.dao.GenreDaoJDBC;
import ru.otus.spring.jdbc.domain.Author;
import ru.otus.spring.jdbc.domain.Book;
import ru.otus.spring.jdbc.domain.Genre;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import({ BookDaoJDBC.class, GenreDaoJDBC.class, AuthorDaoJDBC.class })
public class BookDaoJDBCTest {

	private static final Book EXPECTED_BOOK = new Book((long) 1, "War and Peace", new Author((long) 1, "L.N. Tolstoy"),
			new Genre((long) 1, "novel"));

	@Autowired
	private BookDaoJDBC bookDaoJDBC;

	@DisplayName("возвращать ожидаемое количество книг из БД")
	@Test
	void shouldReturnTheExpectedBookCount() {
		final int EXPECTED_BOOKS_COUNT = 1;
		int acntualBooksCount = bookDaoJDBC.count();
		assertThat(acntualBooksCount).isEqualTo(EXPECTED_BOOKS_COUNT);

	}

	@DisplayName("добавляет книгу в БД с генерацией ИД")
	@Test
	void shouldInsertBook() {
		final Book EXPECTED_BOOK_WITHOUT_GIVEN_ID = bookDaoJDBC
				.create(new Book("TestName", new Author((long) 1, "L.N. Tolstoy"), new Genre((long) 1, "novel")));
		Book actualBook = bookDaoJDBC.findOne(EXPECTED_BOOK_WITHOUT_GIVEN_ID.getId());
		assertThat(actualBook).usingRecursiveComparison().isEqualTo(EXPECTED_BOOK_WITHOUT_GIVEN_ID);
	}

	@DisplayName("возвращает ожидаемую книгу по ИД")
	@Test
	void shouldReturnTheExpectedBookById() {
		Book actualBook = bookDaoJDBC.findOne(EXPECTED_BOOK.getId());
		assertThat(actualBook).usingRecursiveComparison().isEqualTo(EXPECTED_BOOK);
	}

	@DisplayName("удалять заданную книгу по ИД")
	@Test
	void shouldCorrectDeleteBookById() {
		final long EXPECTED_BOOK_ID = 1;
		assertThatCode(() -> bookDaoJDBC.findOne(EXPECTED_BOOK_ID));
		bookDaoJDBC.delete(EXPECTED_BOOK_ID);
		assertThatThrownBy(() -> bookDaoJDBC.findOne(EXPECTED_BOOK_ID))
				.isInstanceOf(EmptyResultDataAccessException.class);

	}

	@DisplayName("возвращать ожидаемый список книг (содержит в любом порядке ожидаемую книгу)")
	@Test
	void shouldReturnExpectedBooksList() {
		List<Book> actualBooks = bookDaoJDBC.findAll();
		assertThat(actualBooks).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(EXPECTED_BOOK);
	}

}