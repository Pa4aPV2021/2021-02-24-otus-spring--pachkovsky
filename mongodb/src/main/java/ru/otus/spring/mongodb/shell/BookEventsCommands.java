package ru.otus.spring.mongodb.shell;

import java.util.List;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import ru.otus.spring.mongodb.domain.Book;
import ru.otus.spring.mongodb.service.BookService;

@ShellComponent
public class BookEventsCommands {

	private final BookService bookService;

	BookEventsCommands(BookService bookService) {
		this.bookService = bookService;
	}

	@ShellMethod(value = "create book", key = { "cb", "create-book" })
	public Book createBook(@ShellOption({ "-name" }) String name, @ShellOption({ "-id_author" }) String idAuthor,
			@ShellOption({ "-id_genre" }) String idGenre) {
		return bookService.create(name, idAuthor, idGenre);
	}

	@ShellMethod(value = "find all book", key = { "fab", "find-all-book" })
	public List<Book> findAllBook() {
		return this.bookService.findAll();
	}

	@ShellMethod(value = "delete book", key = { "db", "delete-book" })
	public void deleteBook(@ShellOption({ "-id_book" }) String idBook) {
		this.bookService.deleteById(idBook);
	}

	@ShellMethod(value = "find one book", key = { "fob", "find-one-book" })
	public Book findOneBook(@ShellOption({ "-id_book" }) String idBook) {
		return this.bookService.findById(idBook);
	}

	@ShellMethod(value = "update book", key = { "ub", "update-book" })
	public Book updateBook(@ShellOption({ "-id_book" }) String idBook, @ShellOption({ "-name" }) String name,
			@ShellOption({ "-id_author" }) String idAuthor, @ShellOption({ "id_genre" }) String idGenre) {
		return bookService.update(idBook, name, idAuthor, idGenre);
	}

}