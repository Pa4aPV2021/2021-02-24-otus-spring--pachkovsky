package ru.otus.spring.batch;

import java.util.HashMap;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import ru.otus.spring.batch.h2.dao.PersonDao;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	public final JobBuilderFactory jobBuilderFactory;

	public final StepBuilderFactory stepBuilderFactory;

	public final MongoTemplate mongoTemplate;

	public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			MongoTemplate mongoTemplate) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.mongoTemplate = mongoTemplate;
	}

	@Bean
	public MongoItemReader<ru.otus.spring.batch.mongo.domain.Person> reader() {
		MongoItemReader<ru.otus.spring.batch.mongo.domain.Person> reader = new MongoItemReader<>();
		reader.setTemplate(mongoTemplate);
		reader.setTargetType(ru.otus.spring.batch.mongo.domain.Person.class);
		reader.setSort(new HashMap<>());
		reader.setQuery("{}");
		return reader;
	}

	@Bean
	public RepositoryItemWriter<ru.otus.spring.batch.h2.domain.Person> writer(PersonDao personDao) {
		RepositoryItemWriter<ru.otus.spring.batch.h2.domain.Person> writer = new RepositoryItemWriter<ru.otus.spring.batch.h2.domain.Person>();
		writer.setRepository(personDao);
		writer.setMethodName("save");
		return writer;
	}

	@Bean
	public Step step1(ItemWriter writer, ItemReader reader) {
		return stepBuilderFactory.get("step1").chunk(10).reader(reader).writer(writer).build();
	}

	@Bean
	public Job importPersonJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importPersonJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1).end().build();
	}

//		@Bean
//		public FlatFileItemReader<PersonH2> readerFile() {
//			return new FlatFileItemReaderBuilder<PersonH2>().name("personItemReader")
//					.resource(new ClassPathResource("sample-data.csv")).delimited()
//					.names(new String[] { "firstName", "lastName" })
//					.fieldSetMapper(new BeanWrapperFieldSetMapper<PersonH2>() {
//						{
//							setTargetType(PersonH2.class);
//						}
//					}).build();
//		}

//		@Bean
//		public PersonItemProcessor processor() {
//			return new PersonItemProcessor();
//		}

//		@Bean
//		public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
//			return new JdbcBatchItemWriterBuilder<Person>()
//					.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>())
//					.sql("INSERT INTO persons (first_name, last_name) VALUES (:firstName, :lastName)")
//					.dataSource(dataSource).build();
//		}

//		@Bean
//		public JpaItemWriter<ru.otus.spring.batch.h2.domain.Person> writer(EntityManagerFactory entityManagerFactory) {
//			
//			JpaItemWriter<ru.otus.spring.batch.h2.domain.Person> itemWriter = new JpaItemWriterBuilder<ru.otus.spring.batch.h2.domain.Person>()
//				      .entityManagerFactory(entityManagerFactory)
//				      .build();
//			return itemWriter;
//		}

}
