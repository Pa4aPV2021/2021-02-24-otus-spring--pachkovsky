package ru.otus.spring.mongodb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import lombok.val;
import ru.otus.spring.mongodb.dao.BookDao;
import ru.otus.spring.mongodb.dao.CommentDao;
import ru.otus.spring.mongodb.domain.Book;
import ru.otus.spring.mongodb.events.MongoBookCascadeDeleteEventsListener;
import ru.otus.spring.mongodb.events.MongoCommentCascadeDeleteEventsListener;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({ "ru.otus.spring.mongodb.dao" })
@DisplayName("CommentRepository при наличии listener-ов в контексте ")
@Import(MongoCommentCascadeDeleteEventsListener.class)
public class CommentRepositoryWithListenerTest {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private CommentDao commentDao;

	@DisplayName("при удалении комментария должен удалить его из коллекции книги")
	@Test
	void shouldRemoveKnowledgeFromStudentExperienceWhenRemovingKnowledge() {

		// Загрузка книги и ее первого комментария
		val books = bookDao.findAll();
		val book = books.get(1);
		val comments = book.getComments();
		val firstComment = comments.get(0);

		// Удаление комментария из коллекции комментариев
		commentDao.delete(firstComment);
	
		// Загружаем размер массива с помощью агрегаций и проверяем, что размер массива
		// в БД тоже изменился
		val expectedСommentsArrayLength = comments.size() - 1;
		val actualCommentArrayLength = bookDao.getCommentsArrayLengthByBookId(book.getId());
		assertThat(actualCommentArrayLength).isEqualTo(expectedСommentsArrayLength);
	}

}
