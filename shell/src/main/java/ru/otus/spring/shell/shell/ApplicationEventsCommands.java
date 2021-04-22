package ru.otus.spring.shell.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import ru.otus.spring.shell.service.TestingStudentsService;

@ShellComponent
public class ApplicationEventsCommands {

	private TestingStudentsService testingStudentsService;

	@Autowired
	public ApplicationEventsCommands(TestingStudentsService testingStudentsService) {
		super();
		this.testingStudentsService = testingStudentsService;
	}

	@ShellMethod(value = "start test command", key = { "s", "start" })
	public void start() {
		testingStudentsService.start();
	}
}