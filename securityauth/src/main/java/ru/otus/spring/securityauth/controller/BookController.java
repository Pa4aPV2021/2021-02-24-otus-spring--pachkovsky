package ru.otus.spring.securityauth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.otus.spring.securityauth.domain.Book;
import ru.otus.spring.securityauth.service.AuthorService;
import ru.otus.spring.securityauth.service.BookService;
import ru.otus.spring.securityauth.service.GenreService;

@Controller
public class BookController {

	private final BookService bookService;
	private final AuthorService authorService;
	private final GenreService genreService;

	@Autowired
	public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
		this.bookService = bookService;
		this.authorService = authorService;
		this.genreService = genreService;
	}

	@GetMapping("/book")
	public String listPage(Model model) {
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		return "book";
	}

	@GetMapping("/book/edit")
	public String editPage(@RequestParam("id") Long id, Model model) {
		model.addAttribute("book", bookService.findById(id));
		model.addAttribute("authors", authorService.findAll());
		model.addAttribute("genres", genreService.findAll());
		return "edit";
	}

	@GetMapping("/book/create")
	public String createPage(Model model) {
		model.addAttribute("authors", authorService.findAll());
		model.addAttribute("genres", genreService.findAll());
		return "create";
	}

	@PostMapping("/book/edit")
	public String editBook(Long id, Book updatedbook) {
		bookService.update(updatedbook);
		return "redirect:/book";
	}

	@PostMapping("/book/delete")
	public String editBook(Long id) {
		bookService.deleteById(id);
		return "redirect:/book";
	}

	@PostMapping("/book/create")
	public String createBook(Book createdbook) {
		bookService.create(createdbook);
		return "redirect:/book";
	}
}
