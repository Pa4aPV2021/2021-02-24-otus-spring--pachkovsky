package ru.otus.spring.batch.batchconfig;

import java.util.HashMap;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import ru.otus.spring.batch.dao.entity.AuthorEntityDao;
import ru.otus.spring.batch.dao.entity.BookEntityDao;
import ru.otus.spring.batch.dao.entity.CommentEntityDao;
import ru.otus.spring.batch.dao.entity.GenreEntityDao;
import ru.otus.spring.batch.domain.document.AuthorDocument;
import ru.otus.spring.batch.domain.document.BookDocument;
import ru.otus.spring.batch.domain.document.CommentDocument;
import ru.otus.spring.batch.domain.document.GenreDocument;
import ru.otus.spring.batch.domain.entity.AuthorEntity;
import ru.otus.spring.batch.domain.entity.GenreEntity;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	public final JobBuilderFactory jobBuilderFactory;

	public final StepBuilderFactory stepBuilderFactory;

	public final MongoTemplate mongoTemplate;

	public final GenreEntityDao genreEntityDao;

	public final AuthorEntityDao authorEntityDao;

	public final BookEntityDao bookEntityDao;
	
	public final CommentEntityDao commentEntityDao;


	
	public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			MongoTemplate mongoTemplate, GenreEntityDao genreEntityDao, AuthorEntityDao authorEntityDao,
			BookEntityDao bookEntityDao, CommentEntityDao commentEntityDao) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.mongoTemplate = mongoTemplate;
		this.genreEntityDao = genreEntityDao;
		this.authorEntityDao = authorEntityDao;
		this.bookEntityDao = bookEntityDao;
		this.commentEntityDao = commentEntityDao;
	}

	private MongoItemReader<CommentDocument> readerMongoComment() {
		MongoItemReader<CommentDocument> reader = new MongoItemReader<CommentDocument>();
		reader.setTemplate(mongoTemplate);
		reader.setTargetType(CommentDocument.class);
		reader.setSort(new HashMap<>());
		reader.setQuery("{}");
		return reader;
	}

	private MongoItemReader<BookDocument> readerMongoBook() {
		MongoItemReader<BookDocument> reader = new MongoItemReader<BookDocument>();
		reader.setTemplate(mongoTemplate);
		reader.setTargetType(BookDocument.class);
		reader.setSort(new HashMap<>());
		reader.setQuery("{}");
		return reader;
	}

	private MongoItemReader<GenreDocument> readerGenre() {
		MongoItemReader<GenreDocument> reader = new MongoItemReader<GenreDocument>();
		reader.setTemplate(mongoTemplate);
		reader.setTargetType(GenreDocument.class);
		reader.setSort(new HashMap<>());
		reader.setQuery("{}");
		return reader;
	}

	private MongoItemReader<AuthorDocument> readerAuthor() {
		MongoItemReader<AuthorDocument> reader = new MongoItemReader<AuthorDocument>();
		reader.setTemplate(mongoTemplate);
		reader.setTargetType(AuthorDocument.class);
		reader.setSort(new HashMap<>());
		reader.setQuery("{}");
		return reader;
	}

	private RepositoryItemWriter<AuthorEntity> writerAuthor() {
		RepositoryItemWriter<AuthorEntity> writer = new RepositoryItemWriter<AuthorEntity>();
		writer.setRepository(authorEntityDao);
		writer.setMethodName("save");
		return writer;
	}

	private RepositoryItemWriter<GenreEntity> writerGenre() {
		RepositoryItemWriter<GenreEntity> writer = new RepositoryItemWriter<GenreEntity>();
		writer.setRepository(genreEntityDao);
		writer.setMethodName("save");
		return writer;
	}

	private ItemWriter writerBook() {
		return new BookItemWriter(genreEntityDao, authorEntityDao, bookEntityDao);
	}
	
	private ItemWriter writerComment() {
		return new CommentItemWriter(bookEntityDao, commentEntityDao);
	}


	private ItemProcessor processorAuthor() {
		return new AuthorItemProcessor();
	}

	private ItemProcessor processorGenre() {
		return new GenreItemProcessor();
	}

	@Bean
	public Step stepGenre() {
		return stepBuilderFactory.get("stepGenre").chunk(10).reader(readerGenre()).processor(processorGenre())
				.writer(writerGenre()).build();
	}

	@Bean
	public Step stepAuthor() {
		return stepBuilderFactory.get("stepAuthor").chunk(10).reader(readerAuthor()).processor(processorAuthor())
				.writer(writerAuthor()).build();
	}

	@Bean
	public Step stepBook() {
		return stepBuilderFactory.get("stepBook").chunk(10).reader(readerMongoBook()).writer(writerBook()).build();
	}
	
	@Bean
	public Step stepComment() {
		return stepBuilderFactory.get("stepComment").chunk(10).reader(readerMongoComment()).writer(writerComment()).build();
	}


	@Bean
	public Job bookJob() {
		return this.jobBuilderFactory.get("bookJob").start(stepGenre()).next(stepAuthor()).next(stepBook()).next(stepComment()).build();
	}

}
