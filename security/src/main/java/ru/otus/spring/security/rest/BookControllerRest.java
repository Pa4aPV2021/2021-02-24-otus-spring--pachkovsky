package ru.otus.spring.security.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.otus.spring.security.rest.dto.BookDto;
import ru.otus.spring.security.service.BookService;


@RestController
public class BookControllerRest {

	private final BookService bookService;

	@Autowired
	public BookControllerRest(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/api/books")
	public List<BookDto> getAllBooks() {
		return bookService.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
	}
}
