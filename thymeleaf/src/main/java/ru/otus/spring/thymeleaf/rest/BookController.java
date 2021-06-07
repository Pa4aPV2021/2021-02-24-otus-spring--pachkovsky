package ru.otus.spring.thymeleaf.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.thymeleaf.domain.Book;
import ru.otus.spring.thymeleaf.service.AuthorService;
import ru.otus.spring.thymeleaf.service.BookService;
import ru.otus.spring.thymeleaf.service.GenreService;

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

	@GetMapping("/")
	public String listPage(Model model) {
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		return "book";
	}

	@GetMapping("/edit")
	public String editPage(@RequestParam("id") Long id, Model model) {
		model.addAttribute("book", bookService.findById(id));
		model.addAttribute("authors", authorService.findAll());
		model.addAttribute("genres", genreService.findAll());
		return "edit";
	}

	@GetMapping("/create")
	public String createPage(Model model) {
		model.addAttribute("authors", authorService.findAll());
		model.addAttribute("genres", genreService.findAll());
		return "create";
	}

	@PostMapping("/edit")
	public String editBook(Long id, Book updatedbook, Model model) {
		model.addAttribute("book", bookService.update(updatedbook));
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		return "book";
	}

	@PostMapping("/delete")
	public String editBook(Long id, Model model) {
System.out.println(id);
		bookService.deleteById(id);
		model.addAttribute("books", bookService.findAll());
		return "book";
	}

	@PostMapping("/create")
	public String createBook(Book createdbook, Model model) {
		model.addAttribute("book", bookService.create(createdbook));
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		return "book";
	}
}
