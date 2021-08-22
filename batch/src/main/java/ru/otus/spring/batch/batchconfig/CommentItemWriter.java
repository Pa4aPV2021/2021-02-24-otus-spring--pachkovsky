package ru.otus.spring.batch.batchconfig;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemWriter;

import ru.otus.spring.batch.dao.entity.BookEntityDao;
import ru.otus.spring.batch.dao.entity.CommentEntityDao;
import ru.otus.spring.batch.domain.document.BookDocument;
import ru.otus.spring.batch.domain.document.CommentDocument;
import ru.otus.spring.batch.domain.entity.BookEntity;
import ru.otus.spring.batch.domain.entity.CommentEntity;

public class CommentItemWriter implements ItemWriter<CommentDocument> {

	public final BookEntityDao bookEntityDao;
	public final CommentEntityDao commentEntityDao;

	public CommentItemWriter(BookEntityDao bookEntityDao, CommentEntityDao commentEntityDao) {
		this.commentEntityDao = commentEntityDao;
		this.bookEntityDao = bookEntityDao;
	}

	@Override
	public void write(List<? extends CommentDocument> documentComments) throws Exception {
		List<BookEntity> entityBooks = bookEntityDao.findAll();
		commentEntityDao.saveAll(documentComments.stream()
				.map(documentComment -> this.process(documentComment, entityBooks)).collect(Collectors.toList()));
	}

	private CommentEntity process(CommentDocument documentComment, List<BookEntity> entityBooks) {
		CommentEntity commentEntity = new CommentEntity();
		commentEntity.setText(documentComment.getText());
		commentEntity.setBook(this.transformGenreDocToGenreEntity(documentComment.getBook(), entityBooks));
		return commentEntity;
	}

	private BookEntity transformGenreDocToGenreEntity(BookDocument bookDoc, List<BookEntity> entityBooks) {

		for (BookEntity entityBook : entityBooks) {
			String bookMongoId = entityBook.getBookEntityMatch().getBookMongoId();

			if (bookMongoId.equals(bookDoc.getId())) {
				return entityBook;
			}

		}

		return null;
	}

}