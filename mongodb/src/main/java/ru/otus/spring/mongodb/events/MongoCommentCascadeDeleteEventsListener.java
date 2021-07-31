package ru.otus.spring.mongodb.events;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.mongodb.dao.BookDao;
import ru.otus.spring.mongodb.domain.Comment;

@Component
@RequiredArgsConstructor
public class MongoCommentCascadeDeleteEventsListener extends AbstractMongoEventListener<Comment> {


	private final BookDao bookRepository;

	@Override
	public void onAfterDelete(AfterDeleteEvent<Comment> event) {
		super.onAfterDelete(event);
		var source = event.getSource();
		var id = source.get("_id").toString();
		bookRepository.removeCommentArrayElementsById(id);
	}
}