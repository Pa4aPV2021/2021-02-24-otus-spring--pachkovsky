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

import ru.otus.spring.batch.h2.dao.PersonDao;

@SpringBootTest
@SpringBatchTest
@DisplayName("ImportPersonJobTest")
class ImportPersonJobTest {

	public static final String IMPORT_PERSON_JOB_NAME = "importPersonJob";

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private PersonDao personDao;

	@Test
	void testJob() throws Exception {

		assertThat(personDao.findAll().size()).isEqualTo(0).as("До миграции есть люди. Продолжение теста невозможно");

		Job job = jobLauncherTestUtils.getJob();
		assertThat(job).isNotNull().extracting(Job::getName).isEqualTo(IMPORT_PERSON_JOB_NAME);

		JobExecution jobExecution = jobLauncherTestUtils.launchJob(new JobParameters());

		assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

		assertThat(personDao.findAll().size()).isNotEqualTo(0).as("После миграции люди отсутствуют");

	}
}
