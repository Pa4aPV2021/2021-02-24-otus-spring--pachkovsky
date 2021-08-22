package ru.otus.spring.batch.batchconfig;

import org.springframework.batch.item.ItemProcessor;

import ru.otus.spring.batch.domain.document.GenreDocument;
import ru.otus.spring.batch.domain.entity.GenreEntity;
import ru.otus.spring.batch.domain.entity.GenreEntityMatch;

public class GenreItemProcessor implements ItemProcessor<GenreDocument, GenreEntity> {

	@Override
	public GenreEntity process(GenreDocument bookDocument) throws Exception {
		GenreEntityMatch genreEntityMatch = new GenreEntityMatch();
		GenreEntity genreEntity = new GenreEntity();
		genreEntity.setName(bookDocument.getName());
		genreEntityMatch.setGenreMongoId(bookDocument.getId());
		genreEntityMatch.setGenreEntity(genreEntity);
		genreEntity.setGenreEntityMatch(genreEntityMatch);
		return genreEntity;
	}

}
