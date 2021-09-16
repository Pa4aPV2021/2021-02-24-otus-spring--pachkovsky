package ru.otus.spring.springdata.shell;

import java.util.List;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import ru.otus.spring.springdata.domain.Genre;
import ru.otus.spring.springdata.service.GenreService;


@ShellComponent
public class GenreEventsCommands {

	private final GenreService genreService;

	GenreEventsCommands(GenreService genreService) {
		this.genreService = genreService;
	}

	@ShellMethod(value = "find all genre", key = { "fag", "find-all-genre" })
	public List<Genre> findAllGenre() {
		return this.genreService.findAll();
	}

}
