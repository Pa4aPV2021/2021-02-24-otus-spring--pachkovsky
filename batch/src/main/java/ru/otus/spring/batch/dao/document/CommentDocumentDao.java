package ru.otus.spring.batch.dao.document;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import ru.otus.spring.batch.domain.document.CommentDocument;

public interface CommentDocumentDao extends MongoRepository<CommentDocument, String> {

}
