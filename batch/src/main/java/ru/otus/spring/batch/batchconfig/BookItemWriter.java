package ru.otus.spring.batch.batchconfig;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemWriter;

import ru.otus.spring.batch.dao.entity.AuthorEntityDao;
import ru.otus.spring.batch.dao.entity.BookEntityDao;
import ru.otus.spring.batch.dao.entity.GenreEntityDao;
import ru.otus.spring.batch.domain.document.AuthorDocument;
import ru.otus.spring.batch.domain.document.BookDocument;
import ru.otus.spring.batch.domain.document.GenreDocument;
import ru.otus.spring.batch.domain.entity.AuthorEntity;
import ru.otus.spring.batch.domain.entity.BookEntity;
import ru.otus.spring.batch.domain.entity.BookEntityMatch;
import ru.otus.spring.batch.domain.entity.GenreEntity;

public class BookItemWriter implements ItemWriter<BookDocument> {

	public final GenreEntityDao genreEntityDao;

	public final AuthorEntityDao authorEntityDao;

	public final BookEntityDao bookEntityDao;                        

	public BookItemWriter(GenreEntityDao genreEntityDao, AuthorEntityDao authorEntityDao, BookEntityDao bookEntityDao) {
		this.genreEntityDao = genreEntityDao;
		this.authorEntityDao = authorEntityDao;
		this.bookEntityDao = bookEntityDao;
	}

	@Override
	public void write(List<? extends BookDocument> documentBooks) throws Exception {
		List<AuthorEntity> entityAuthors = authorEntityDao.findAll();
		List<GenreEntity> entityGeners = genreEntityDao.findAll();
		bookEntityDao.saveAll(
				documentBooks.stream().map(documentBook -> this.process(documentBook, entityAuthors, entityGeners))
						.collect(Collectors.toList()));
	}

	public BookEntity process(BookDocument bookDocument, List<AuthorEntity> entityAuthors,
			List<GenreEntity> entityGeners) {
	
		BookEntityMatch bookEntityMatch = new BookEntityMatch();
		BookEntity bookEntity = new BookEntity();
		bookEntity.setName(bookDocument.getName());
		bookEntity.setAuthor(this.transformAuthorDocToAuthorEntity(bookDocument.getAuthor(), entityAuthors));
		bookEntity.setGenre(this.transformGenreDocToGenreEntity(bookDocument.getGenre(), entityGeners));
		bookEntityMatch.setBookMongoId(bookDocument.getId());
		bookEntityMatch.setBookEntity(bookEntity);
		bookEntity.setBookEntityMatch(bookEntityMatch);
		return bookEntity;
	}

	private AuthorEntity transformAuthorDocToAuthorEntity(AuthorDocument authorDoc, List<AuthorEntity> entityAuthors) {

		for (AuthorEntity entityAuthor : entityAuthors) {
			String authorMongoId = entityAuthor.getAuthorEntityMatch().getAuthorMongoId();

			if (authorMongoId.equals(authorDoc.getId())) {
				return entityAuthor;
			}

		}

		return null;
	}

	private GenreEntity transformGenreDocToGenreEntity(GenreDocument genreDoc, List<GenreEntity> entityGeners) {

		for (GenreEntity entityGenre : entityGeners) {
			String genreMongoId = entityGenre.getGenreEntityMatch().getGenreMongoId();

			if (genreMongoId.equals(genreDoc.getId())) {
				return entityGenre;
			}

		}

		return null;
	}

}