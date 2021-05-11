package ru.otus.spring.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import lombok.val;
import ru.otus.spring.jpa.dao.BookDaoJPA;
import ru.otus.spring.jpa.domain.Author;
import ru.otus.spring.jpa.domain.Book;
import ru.otus.spring.jpa.domain.Genre;

@DisplayName("Dao для работы с книгами должно")
@DataJpaTest
@Import({ BookDaoJPA.class })
public class BookDaoJDBCTest {

	private static final Book EXPECTED_BOOK = new Book((long) 1, "War and Peace", new Author((long) 1, "L.N. Tolstoy"),
			new Genre((long) 1, "novel"));

	private static final long FIRST_BOOK_ID = 1L;

	@Autowired
	private TestEntityManager em;

	@Autowired
	private BookDaoJPA bookDaoJPA;

	@SuppressWarnings("deprecation")
	@DisplayName("добавляет книгу в БД с генерацией ИД")
	@Test
	void shouldInsertBook() {

		val author = new Author(1L, "L.N. Tolstoy");
		val genre = new Genre(1L, "novel");
		val expectedBook = new Book("TestName", author, genre);

		bookDaoJPA.create(expectedBook);
		assertThat(expectedBook.getId()).isNotNull();

		Book actualBook = bookDaoJPA.findOne(EXPECTED_BOOK_WITHOUT_GIVEN_ID.getId())
				.orElseThrow(() -> new RuntimeException("book: " + EXPECTED_BOOK.getId() + " not found"));
		assertThat(actualBook).usingRecursiveComparison().isEqualTo(EXPECTED_BOOK_WITHOUT_GIVEN_ID);
		
		val actualBook = em.find(Book.class, expectedBook.getId());
		assertThat(actualBook).isNotNull().matches(b -> !b.getName().equals(""))
				.matches(b -> b.getAuthor() != null && b.getAuthor().getId())
				.matches(b -> b.getAvatar() != null).matches(b -> b.getEmails() != null && b.getEmails().size() > 0);
	}

	@DisplayName("возвращать ожидаемый список книг (содержит в любом порядке ожидаемую книгу)")
	@Test
	void shouldReturnExpectedBooksList() {
		List<Book> actualBooks = bookDaoJPA.findAll();
		assertThat(actualBooks).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(EXPECTED_BOOK);
	}

	@DisplayName("возвращает ожидаемую книгу по ИД")
	@Test
	void shouldReturnTheExpectedBookById() {
		Book actualBook = bookDaoJPA.findOne(EXPECTED_BOOK.getId())
				.orElseThrow(() -> new RuntimeException("book: " + EXPECTED_BOOK.getId() + " not found"));
		assertThat(actualBook).usingRecursiveComparison().isEqualTo(EXPECTED_BOOK);
	}

	@DisplayName("удалять заданную книгу по ИД")
	@Test
	void shouldCorrectDeleteBookById() {
		final long EXPECTED_BOOK_ID = 1;
		assertThatCode(() -> bookDaoJPA.findOne(EXPECTED_BOOK_ID));
		Book actualBook = bookDaoJPA.findOne(EXPECTED_BOOK.getId())
				.orElseThrow(() -> new RuntimeException("book: " + EXPECTED_BOOK.getId() + " not found"));
		bookDaoJPA.delete(EXPECTED_BOOK_ID);
		assertThatThrownBy(() -> bookDaoJPA.findOne(EXPECTED_BOOK_ID).get().getId())
				.isInstanceOf(NoSuchElementException.class);

	}

}