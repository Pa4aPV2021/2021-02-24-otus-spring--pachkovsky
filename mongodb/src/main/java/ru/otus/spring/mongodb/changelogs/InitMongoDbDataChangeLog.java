package ru.otus.spring.mongodb.changelogs;

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

	@ChangeSet(order = "003", id = "insertCommentsWithBook", author = "PV", runAlways = true)
	public void insertCommentsWithBook(BookDao bookDao, CommentDao commentDao) {

		var commentOneInBookOne = commentDao.save(new Comment("commentOneInBookOne"));
		var commentTwoInBookOne = commentDao.save(new Comment("commentTwoInBookOne"));

		Book bookOneWithTwoComment = new Book("bookOneWithTwoComment", tolstoyAuthor, novelGenre);
		bookOneWithTwoComment.addComment(commentOneInBookOne);
		bookOneWithTwoComment.addComment(commentTwoInBookOne);
		bookDao.save(bookOneWithTwoComment);
		commentDao.save(commentOneInBookOne);
		commentDao.save(commentTwoInBookOne);

		var commentThreeInBookTwo = commentDao.save(new Comment("commentThreeInBookTwo"));
		var bookTwoWithOneComment = new Book("bookTwoWithOneComment", tolstoyAuthor, novelGenre);
		bookTwoWithOneComment.addComment(commentThreeInBookTwo);
		bookDao.save(bookTwoWithOneComment);
		commentDao.save(commentThreeInBookTwo);
	}

}
