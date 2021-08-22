package ru.otus.spring.batch.changelogs;

import java.util.Arrays;
import java.util.List;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;

import ru.otus.spring.batch.dao.document.AuthorDocumentDao;
import ru.otus.spring.batch.dao.document.BookDocumentDao;
import ru.otus.spring.batch.dao.document.CommentDocumentDao;
import ru.otus.spring.batch.dao.document.GenreDocumentDao;
import ru.otus.spring.batch.domain.document.AuthorDocument;
import ru.otus.spring.batch.domain.document.BookDocument;
import ru.otus.spring.batch.domain.document.CommentDocument;
import ru.otus.spring.batch.domain.document.GenreDocument;
import ru.otus.spring.batch.domain.document.PersonDocument;

@ChangeLog(order = "001")
public class InitMongoDbBookDataChangeLog {

	private AuthorDocument tolstoyAuthor;
	private GenreDocument adventuresGenre;
	private List<CommentDocument> commentsForBookOne;
	private List<CommentDocument> commentsForBookTwo;
	private GenreDocument novelGenre;

	@ChangeSet(order = "000", id = "dropDB", author = "PV", runAlways = true)
	public void dropDB(MongoDatabase mongoDatabase) {
		mongoDatabase.drop();
	}

	@ChangeSet(order = "001", id = "addAuthors", author = "PV", runAlways = true)
	public void insertAuthors(AuthorDocumentDao authorDao) {
		this.tolstoyAuthor = authorDao.save(new AuthorDocument("L.N. Tolstoy"));
		authorDao.save(new AuthorDocument("J.G. Verne"));
	}

	@ChangeSet(order = "002", id = "addGenres", author = "PV", runAlways = true)
	public void insertGenres(GenreDocumentDao genreDao) {
		this.novelGenre =	genreDao.save(new GenreDocument("novel"));
		this.adventuresGenre = genreDao.save(new GenreDocument("adventures"));

	}

	@ChangeSet(order = "003", id = "insertComments", author = "PV", runAlways = true)
	public void insertComments(BookDocumentDao bookDao, CommentDocumentDao commentDao) {

		commentsForBookOne = commentDao.saveAll(
				Arrays.asList(new CommentDocument("commentOneInBookOne"), new CommentDocument("commentTwoInBookOne")));

		commentsForBookTwo = commentDao.saveAll(Arrays.asList(new CommentDocument("commentThreeInBookTwo")));
	}

	@ChangeSet(order = "004", id = "insertBooksAndUpdateCommentsBookInfo", author = "PV", runAlways = true)
	public void insertBooksAndUpdateCommentsBookInfo(BookDocumentDao bookDao, CommentDocumentDao commentDao) {

		var bookOneWithTwoComment = new BookDocument("bookOneWithTwoComment", tolstoyAuthor, adventuresGenre,
				commentsForBookOne);
		bookDao.save(bookOneWithTwoComment);
		commentDao.saveAll(bookOneWithTwoComment.getComments());

		var bookTwoWithOneComment = new BookDocument("bookTwoWithOneComment", tolstoyAuthor, novelGenre,
				commentsForBookTwo);
		bookDao.save(bookTwoWithOneComment);
		commentDao.saveAll(bookTwoWithOneComment.getComments());
	}

	@ChangeSet(order = "005", id = "initPersons", author = "PV", runAlways = true)
	public void initPersons(MongockTemplate template) {
		template.save(new PersonDocument("Jill", "Doe"));
		template.save(new PersonDocument("Joe", "Doe"));
		template.save(new PersonDocument("Joe", "Doe"));
	}

}
