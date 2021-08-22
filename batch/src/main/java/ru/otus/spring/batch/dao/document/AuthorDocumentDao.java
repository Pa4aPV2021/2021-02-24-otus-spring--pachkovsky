package ru.otus.spring.batch.dao.document;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.otus.spring.batch.domain.document.AuthorDocument;


public interface AuthorDocumentDao extends MongoRepository<AuthorDocument, String> {
}
