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
import ru.otus.spring.jpa.dao.CommentDaoJPA;
import ru.otus.spring.jpa.domain.Book;
import ru.otus.spring.jpa.domain.Comment;


@DisplayName("Dao для работы с комментариями должно")
@DataJpaTest
@Import({ CommentDaoJPA.class })
public class CommentDaoJPATest {

	private static final long FIRST_COMMENT_ID = 1L;
	private static final int EXPECTED_NUMBER_COMMENTS = 2;

	@Autowired
	private TestEntityManager em;

	@Autowired
	private CommentDaoJPA commentDaoJPA;

	@DisplayName("возвращает ожидаемый комментари по ИД")
	@Test
	void shouldReturnTheExpectedCommentById() {
		val actualComment = commentDaoJPA.findOne(FIRST_COMMENT_ID).get();
		val expectedComment = em.find(Comment.class, FIRST_COMMENT_ID);
		assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
	}

	@DisplayName("добавляет коменнтарий в БД с генерацией ИД")
	@Test
	void shouldInsertComment() {
		val expectedComment = new Comment("textComment", new Book(1L));

		commentDaoJPA.create(expectedComment);
		assertThat(expectedComment.getId()).isNotNull();

		Comment actualComment = em.find(Comment.class, expectedComment.getId());
		assertThat(actualComment).usingRecursiveComparison().isEqualTo(actualComment);
	}

	@DisplayName("загружать список комментариев для книги")
	@Test
	void shouldReturnExpectedCommentsListByBook() {
		List<Comment> actualComments = commentDaoJPA.findAllForBook(1L);
		assertThat(actualComments).isNotNull().hasSize(EXPECTED_NUMBER_COMMENTS).allMatch(c -> !c.getText().equals(""))
		.allMatch(s -> s.getBook() != null);
	}

	@DisplayName("удалять заданный комментарий по ИД")
	@Test
	void shouldCorrectDeleteCommentById() {
		val firstComment = em.find(Comment.class, FIRST_COMMENT_ID);
		assertThat(firstComment).isNotNull();
		em.detach(firstComment);
		commentDaoJPA.delete(FIRST_COMMENT_ID);
		val deletedComment = em.find(Comment.class, FIRST_COMMENT_ID);
		assertThat(deletedComment).isNull();
	}
	
	
	@DisplayName("не удалять книгу при удалении комента")
	@Test
	void shouldNotDeleteBookWhenDeletingComment() {
		var commentForRemove = em.find(Comment.class, FIRST_COMMENT_ID);
		var commentBookId = commentForRemove.getBook().getId();
		em.remove(commentForRemove);
		em.flush();
		Book book = em.find(Book.class, commentBookId);
		assertThat(book).isNotNull();
	}

}