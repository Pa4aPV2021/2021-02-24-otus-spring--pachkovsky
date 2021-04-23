package ru.otus.spring.jdbc.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import ru.otus.spring.jdbc.domain.Book;
import ru.otus.spring.jdbc.service.BookService;
import ru.otus.spring.jdbc.service.MessageSourceLocalized;
import ru.otus.spring.jdbc.service.OutputInputService;

@ShellComponent
public class ApplicationEventsCommands {

	private final OutputInputService outputInputService;
	private final MessageSourceLocalized messageSourceLocalized;
	private final BookService bookService;

	ApplicationEventsCommands(BookService bookService, OutputInputService outputInputService,
			MessageSourceLocalized messageSourceLocalized) {
		this.bookService = bookService;
		this.outputInputService = outputInputService;
		this.messageSourceLocalized = messageSourceLocalized;
	}

	@ShellMethod(value = "create book", key = { "cb", "create-book" })
	public void createBook() {
		String name = outputInputService.requestForInput(messageSourceLocalized.getMessage("create-book.name"));
		String id_author = outputInputService
				.requestForInput(messageSourceLocalized.getMessage("create-book.id_author"));
		String id_genre = outputInputService.requestForInput(messageSourceLocalized.getMessage("create-book.id_genre"));
		Book newBook = bookService.create(name, Long.valueOf(id_author), Long.valueOf(id_genre));
		outputInputService.requestForOutput(
				messageSourceLocalized.getMessage("create-book-info.create-success", newBook.toString()));
	}

	@ShellMethod(value = "find all book", key = { "fab", "find-all-book" })
	public void findAllBook() {
		this.bookService.findAll().forEach(book -> outputInputService.requestForOutput(book.toString()));
	}

	@ShellMethod(value = "delete book", key = { "db", "delete-book" })
	public void deleteBook() {
		String id_book = outputInputService.requestForInput(messageSourceLocalized.getMessage("delete-book.id"));
		this.bookService.delete(Long.valueOf(id_book));
		outputInputService
				.requestForOutput(messageSourceLocalized.getMessage("delete-book-info.delete-success", id_book));
	}

	@ShellMethod(value = "find one book", key = { "fob", "find-one-book" })
	public void findOneBook() {
		String id_book = outputInputService.requestForInput(messageSourceLocalized.getMessage("find-book.id"));
		Book book = this.bookService.findOne(Long.valueOf(id_book));
		outputInputService.requestForOutput(book.toString());
	}

	@ShellMethod(value = "update book", key = { "ub", "update-book" })
	public void updateBook() {
		String id_book = outputInputService.requestForInput(messageSourceLocalized.getMessage("update-book.id"));
		String name = outputInputService.requestForInput(messageSourceLocalized.getMessage("update-book.name"));
		String id_author = outputInputService
				.requestForInput(messageSourceLocalized.getMessage("update-book.id_author"));
		String id_genre = outputInputService.requestForInput(messageSourceLocalized.getMessage("update-book.id_genre"));
		Book updatedBook = bookService.update(Long.valueOf(id_book), name, Long.valueOf(id_author),
				Long.valueOf(id_genre));
		outputInputService.requestForOutput(
				messageSourceLocalized.getMessage("update-book-info.update-success", updatedBook.toString()));
	}

}