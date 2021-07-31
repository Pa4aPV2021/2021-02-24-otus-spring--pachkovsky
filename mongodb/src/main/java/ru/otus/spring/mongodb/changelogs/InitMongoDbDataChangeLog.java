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

//	для удовлетворения двунаправленной связи добавил метод addComment, это правильно?
	@ChangeSet(order = "003", id = "insertBookWithComments", author = "PV", runAlways = true)
	public void insertBookWithComments(BookDao bookDao, CommentDao commentDao) {
		var bookTwoWithOneComment = bookDao.save(new Book("bookTwoWithOneComment", tolstoyAuthor, novelGenre));
		bookTwoWithOneComment.addComment(commentDao.save(new Comment("commentThreeInBookTwo", bookTwoWithOneComment)));
	}

	@ChangeSet(order = "004", id = "insertCommentsWithBook", author = "PV", runAlways = true)
	public void insertCommentsWithBook(BookDao bookDao, CommentDao commentDao) {
		Book bookOneWithTwoComment = new Book("bookOneWithTwoComment", tolstoyAuthor, novelGenre);
		var commentOneInBookOne = new Comment("commentOneInBookOne");
		var commentTwoInBookOne = new Comment("commentTwoInBookOne");
		bookOneWithTwoComment.addComment(commentDao.save(commentOneInBookOne));
		bookOneWithTwoComment.addComment(commentDao.save(commentTwoInBookOne));
		bookDao.save(bookOneWithTwoComment);
	}

}
