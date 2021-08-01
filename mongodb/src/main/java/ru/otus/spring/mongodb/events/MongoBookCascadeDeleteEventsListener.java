package ru.otus.spring.mongodb.events;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.mongodb.dao.BookDao;
import ru.otus.spring.mongodb.domain.Book;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeDeleteEventsListener extends AbstractMongoEventListener<Book> {

	private final BookDao bookDao;

	@Override
	public void onAfterDelete(AfterDeleteEvent<Book> event) {
		super.onAfterDelete(event);
		var source = event.getSource();
		var id = source.get("_id").toString();
		bookDao.removeCommentsForBookByBookId(id);
	}

}
