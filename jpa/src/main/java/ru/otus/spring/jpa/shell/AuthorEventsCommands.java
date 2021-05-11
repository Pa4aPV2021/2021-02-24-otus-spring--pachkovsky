package ru.otus.spring.jpa.shell;

import java.util.List;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import ru.otus.spring.jpa.domain.Author;
import ru.otus.spring.jpa.service.AuthorService;


@ShellComponent
public class AuthorEventsCommands {

	private final AuthorService authorService;

	AuthorEventsCommands(AuthorService authorService) {
		this.authorService = authorService;
	}

	@ShellMethod(value = "find all author", key = { "faa", "find-all-author" })
	public List<Author> findAllAuthor() {
		return this.authorService.findAll();
	}

}
