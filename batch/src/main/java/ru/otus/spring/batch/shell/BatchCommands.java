package ru.otus.spring.batch.shell;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import lombok.SneakyThrows;
import ru.otus.spring.batch.dao.entity.BookEntityDao;

@ShellComponent
public class BatchCommands {

	private final Job importPersonJob;

	private final JobLauncher jobLauncher;
	private final BookEntityDao bookDao;

	// http://localhost:8080/h2-console/

	public BatchCommands(Job importPersonJob, JobLauncher jobLauncher, BookEntityDao bookDao) {
		super();
		this.importPersonJob = importPersonJob;
		this.jobLauncher = jobLauncher;
		this.bookDao = bookDao;

	}

	@SneakyThrows
	@ShellMethod(value = "startMigrationJobWithJobLauncher", key = "sm-jl")
	public void startMigrationJobWithJobLauncher() {
		System.out.println("До миграции: " + bookDao.findAll());
		JobExecution execution = jobLauncher.run(importPersonJob, new JobParameters());
		System.out.println("После миграции: " + bookDao.findAll());
		System.out.println(execution);
	}

}