package ru.otus.spring.mongodb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
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
import ru.otus.spring.mongodb.events.MongoBookCascadeDeleteEventsListener;

@Disabled
@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({ "ru.otus.spring.mongodb.dao" })
@DisplayName("BookRepository при наличии listener-ов в контексте ")
@Import(MongoBookCascadeDeleteEventsListener.class)
public class BookRepositoryWithListenerTest {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private CommentDao commentDao;

	@DisplayName("при удалении Book должен удалить все свзанные с ним комменты")
	@Test
	void shouldRemoveCommentsForBookByBookId() {

		// Загрузка книги и сохранение его id
		val idBook = bookDao.findAll().get(0).getId();

		// Загрузка коментов книги и сохранение их количества
		var beforeSizeComments = commentDao.findByBook_Id(idBook).size();

		// Проверяем есть ли коменты
		assertThat(beforeSizeComments).isNotEqualTo(0).as("У книги нет комментариев. Продолжение теста невозможно");

		// Удаляем книгу и инициализируем удаление коммента
		bookDao.deleteById(idBook);

		// пробуем загрузить коменты удfленной книги
		var afterSizeComments = commentDao.findByBook_Id(idBook).size();

		// Комментов не должно остаться
		assertThat(afterSizeComments).isEqualTo(0);

	}

}
