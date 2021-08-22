package ru.otus.spring.batch.batchconfig;

import org.springframework.batch.item.ItemProcessor;

import ru.otus.spring.batch.domain.document.AuthorDocument;
import ru.otus.spring.batch.domain.entity.AuthorEntity;
import ru.otus.spring.batch.domain.entity.AuthorEntityMatch;

public class AuthorItemProcessor implements ItemProcessor<AuthorDocument, AuthorEntity> {

	@Override
	public AuthorEntity process(AuthorDocument bookDocument) throws Exception {
		AuthorEntityMatch authorEntityMatch = new AuthorEntityMatch();
		AuthorEntity authorEntity = new AuthorEntity();
		authorEntity.setName(bookDocument.getName());
		authorEntityMatch.setAuthorMongoId(bookDocument.getId());
		authorEntityMatch.setAuthorEntity(authorEntity);
		authorEntity.setAuthorEntityMatch(authorEntityMatch);
		return authorEntity;
	}

}