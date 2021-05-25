package ru.otus.spring.jpa.shell;

import java.util.List;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import ru.otus.spring.jpa.domain.Book;
import ru.otus.spring.jpa.service.BookService;

@ShellComponent
public class BookEventsCommands {

	private final BookService bookService;

	BookEventsCommands(BookService bookService) {
		this.bookService = bookService;
	}

	@ShellMethod(value = "create book", key = { "cb", "create-book" })
	public Book createBook(@ShellOption({ "-name" }) String name, @ShellOption({ "-id_author" }) Long idAuthor,
			@ShellOption({ "-id_genre" }) Long idGenre) {
		return bookService.create(name, idAuthor, idGenre);
	}

	@ShellMethod(value = "find all book", key = { "fab", "find-all-book" })
	public List<Book> findAllBook() {
		return this.bookService.findAll();
	}

	@ShellMethod(value = "delete book", key = { "db", "delete-book" })
	public void deleteBook(@ShellOption({ "-id_book" }) Long idBook) {
		this.bookService.delete(idBook);
	}

	@ShellMethod(value = "find one book", key = { "fob", "find-one-book" })
	public Book findOneBook(@ShellOption({ "-id_book" }) Long idBook) {
		return this.bookService.findOne(idBook);
	}

	@ShellMethod(value = "update book", key = { "ub", "update-book" })
	public Book updateBook(@ShellOption({ "-id_book" }) Long idBook, @ShellOption({ "-name" }) String name,
			@ShellOption({ "-id_author" }) Long idAuthor, @ShellOption({ "id_genre" }) Long idGenre) {
		return bookService.update(idBook, name, idAuthor, idGenre);
	}

}