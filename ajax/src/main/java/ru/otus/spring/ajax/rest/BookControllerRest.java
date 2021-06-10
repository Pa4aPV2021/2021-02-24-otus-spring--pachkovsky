package ru.otus.spring.ajax.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.otus.spring.ajax.rest.dto.BookDto;
import ru.otus.spring.ajax.service.AuthorService;
import ru.otus.spring.ajax.service.BookService;
import ru.otus.spring.ajax.service.GenreService;

@RestController
public class BookControllerRest {

	private final BookService bookService;

	@Autowired
	public BookControllerRest(BookService bookService, AuthorService authorService, GenreService genreService) {
		this.bookService = bookService;
	}

	@GetMapping("/api/books")
	public List<BookDto> getAllPersons() {
		return bookService.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
	}
}
