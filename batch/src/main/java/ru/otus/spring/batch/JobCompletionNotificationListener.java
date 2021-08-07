package ru.otus.spring.batch;

import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;


@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

//	private final JdbcTemplate jdbcTemplate;
//
//	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
//
//	@Override
//	public void afterJob(JobExecution jobExecution) {
//		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
//
//			jdbcTemplate
//					.query("SELECT first_name, last_name FROM people",
//							(rs, row) -> new Person(rs.getString(1), rs.getString(2)))
//					.forEach(person -> System.out.println("Found <" + person + "> in the database."));
//		}
//	}

}
