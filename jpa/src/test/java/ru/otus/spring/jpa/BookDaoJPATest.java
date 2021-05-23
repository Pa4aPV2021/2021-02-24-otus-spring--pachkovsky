package ru.otus.spring.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
public class BookDaoJPATest {

	private static final long FIRST_BOOK_ID = 1L;

	private static final int EXPECTED_NUMBER_OF_BOOKS = 2;

	@Autowired
	private TestEntityManager em;

	@Autowired
	private BookDaoJPA bookDaoJPA;

	@DisplayName("возвращает ожидаемую книгу по ИД")
	@Test
	void shouldReturnTheExpectedBookById() {
		val actualBook = bookDaoJPA.findOne(FIRST_BOOK_ID).get();
		val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
		assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
	}

	@DisplayName("добавляет книгу в БД с генерацией ИД")
	@Test
	void shouldInsertBook() {

		val author = new Author(1L, "L.N. Tolstoy");
		val genre = new Genre(1L, "novel");
//		val comments = Collections.singletonList(course);
		val expectedBook = new Book("TestName", author, genre);

		bookDaoJPA.create(expectedBook);
		assertThat(expectedBook.getId()).isNotNull();

		Book actualBook = em.find(Book.class, expectedBook.getId());
		assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
	}

	@DisplayName("загружать список всех книг с полной информацией о них")
	@Test
	void shouldReturnExpectedBooksList() {
		List<Book> actualBooks = bookDaoJPA.findAll();
		assertThat(actualBooks).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS).allMatch(s -> !s.getName().equals(""))
				.allMatch(s -> s.getGenre() != null).allMatch(s -> s.getAuthor() != null);
	}

	@DisplayName("удалять заданную книгу по ИД")
	@Test
	void shouldCorrectDeleteBookById() {
		val firstBook = em.find(Book.class, FIRST_BOOK_ID);
		assertThat(firstBook).isNotNull();
		em.detach(firstBook);
		bookDaoJPA.delete(FIRST_BOOK_ID);
		val deletedBook = em.find(Book.class, FIRST_BOOK_ID);
		assertThat(deletedBook).isNull();
	}

}