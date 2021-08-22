package ru.otus.spring.batch.dao.document;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.otus.spring.batch.domain.document.BookDocument;

public interface BookDocumentDao extends MongoRepository<BookDocument, String> {

	
}
