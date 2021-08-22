package ru.otus.spring.batch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.otus.spring.batch.dao.entity.BookEntityDao;

@SpringBootTest
@SpringBatchTest
@DisplayName("bookJobTest")
class ImportPersonJobTest {

	public static final String BOOK_JOB_NAME = "bookJob";

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private BookEntityDao bookEntityDao;

	@Test
	void testJob() throws Exception {

		assertThat(bookEntityDao.findAll().size()).isEqualTo(0).as("До миграции есть книги. Продолжение теста невозможно");

		Job job = jobLauncherTestUtils.getJob();
		assertThat(job).isNotNull().extracting(Job::getName).isEqualTo(BOOK_JOB_NAME);

		JobExecution jobExecution = jobLauncherTestUtils.launchJob(new JobParameters());

		assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

		assertThat(bookEntityDao.findAll().size()).isNotEqualTo(0).as("После миграции книги отсутствуют");

	}
}
