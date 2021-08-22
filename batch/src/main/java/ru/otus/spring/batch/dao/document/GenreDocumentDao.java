package ru.otus.spring.batch.dao.document;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.otus.spring.batch.domain.document.GenreDocument;


public interface GenreDocumentDao extends MongoRepository<GenreDocument, String> {
}
