package ru.otus.spring.securityauth.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.otus.spring.securityauth.rest.dto.BookDto;
import ru.otus.spring.securityauth.service.BookService;

@RestController
public class BookControllerRest {

	private final BookService bookService;

	@Autowired
	public BookControllerRest(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/admin")
	public String onlyAdminPage() {
		return "onlyAdminPage";
	}

	@GetMapping("/api/books")
	public List<BookDto> getAllBooks() {
		return bookService.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
	}
}
