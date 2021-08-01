package ru.otus.spring.mongodb.changelogs;

import java.util.Arrays;
import java.util.List;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;

import ru.otus.spring.mongodb.dao.AuthorDao;
import ru.otus.spring.mongodb.dao.BookDao;
import ru.otus.spring.mongodb.dao.CommentDao;
import ru.otus.spring.mongodb.dao.GenreDao;
import ru.otus.spring.mongodb.domain.Author;
import ru.otus.spring.mongodb.domain.Book;
import ru.otus.spring.mongodb.domain.Comment;
import ru.otus.spring.mongodb.domain.Genre;

@ChangeLog(order = "001")
public class InitMongoDbDataChangeLog {

	private Author tolstoyAuthor;
	private Genre novelGenre;
	private List<Comment> commentsForBookOne;
	private List<Comment> commentsForBookTwo;

	@ChangeSet(order = "000", id = "dropDB", author = "PV", runAlways = true)
	public void dropDB(MongoDatabase mongoDatabase) {
		mongoDatabase.drop();
	}

	@ChangeSet(order = "001", id = "addAuthors", author = "PV", runAlways = true)
	public void insertAuthors(AuthorDao authorDao) {
		this.tolstoyAuthor = authorDao.save(new Author("L.N. Tolstoy"));
		authorDao.save(new Author("J.G. Verne"));
	}

	@ChangeSet(order = "002", id = "addGenres", author = "PV", runAlways = true)
	public void insertGenres(GenreDao genreDao) {
		this.novelGenre = genreDao.save(new Genre("novel"));
		genreDao.save(new Genre("adventures"));
	}

	@ChangeSet(order = "003", id = "insertComments", author = "PV", runAlways = true)
	public void insertComments(BookDao bookDao, CommentDao commentDao) {

		commentsForBookOne = commentDao
				.saveAll(Arrays.asList(new Comment("commentOneInBookOne"), new Comment("commentTwoInBookOne")));

		commentsForBookTwo = commentDao.saveAll(Arrays.asList(new Comment("commentThreeInBookTwo")));
	}

	@ChangeSet(order = "004", id = "insertBooksAndUpdateCommentsBookInfo", author = "PV", runAlways = true)
	public void insertBooksAndUpdateCommentsBookInfo(BookDao bookDao, CommentDao commentDao) {

		var bookOneWithTwoComment = new Book("bookOneWithTwoComment", tolstoyAuthor, novelGenre, commentsForBookOne);
		bookDao.save(bookOneWithTwoComment);
		commentDao.saveAll(bookOneWithTwoComment.getComments());

		var bookTwoWithOneComment = new Book("bookTwoWithOneComment", tolstoyAuthor, novelGenre, commentsForBookTwo);
		bookDao.save(bookTwoWithOneComment);
		commentDao.saveAll(bookTwoWithOneComment.getComments());
	}

}
