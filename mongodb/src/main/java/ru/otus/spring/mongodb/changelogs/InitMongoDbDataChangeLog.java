package ru.otus.spring.mongodb.changelogs;

import java.util.Arrays;

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
	private Book warAndPeaceBook;

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

	@ChangeSet(order = "003", id = "addBooks", author = "PV", runAlways = true)
	public void insertBooks(BookDao bookDao) {
		this.warAndPeaceBook = bookDao.save(new Book("War and Peace", tolstoyAuthor, novelGenre));
	}

	@ChangeSet(order = "004", id = "addCommentss", author = "PV", runAlways = true)
	public void insertCommentss(CommentDao commentDao) {
		commentDao.save(new Comment("one-comment", warAndPeaceBook));
	}

	@ChangeSet(order = "005", id = "addBooksWithComment", author = "PV", runAlways = true)
	public void insertBooksWithComment(CommentDao commentDao, BookDao bookDao) {
		var commentInBookOne = commentDao.save(new Comment("comment-one-in-book-with-Comments"));
		var commentInBookTwo = commentDao.save(new Comment("comment-two-in-book-with-Comments"));
		var bookWithCommentList = new Book("book-with-comment-list", tolstoyAuthor, novelGenre,
				Arrays.asList(commentInBookOne, commentInBookTwo));
		bookDao.save(bookWithCommentList);
	}
}
