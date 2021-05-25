package ru.otus.spring.jpa.shell;

import java.util.List;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import ru.otus.spring.jpa.domain.Comment;
import ru.otus.spring.jpa.service.CommentService;

@ShellComponent
public class CommentEventsCommands {

	private final CommentService commentService;

	CommentEventsCommands(CommentService commentService) {
		this.commentService = commentService;
	}

	@ShellMethod(value = "create comment for book", key = { "ccb", "create-comment-book" })
	public Comment createForBook(@ShellOption({ "-id_book" }) Long idBook,
			@ShellOption({ "-text_comment" }) String textComment) {
		return commentService.createForBook(idBook, textComment);
	}

	@ShellMethod(value = "update comment", key = { "uc", "update-comment" })
	public Comment updateComment(@ShellOption({ "-id_comment" }) Long idComment,
			@ShellOption({ "-text_comment" }) String textComment) {
		return commentService.update(idComment, textComment);
	}

	@ShellMethod(value = "find all comment for book", key = { "facb", "find-all-comment-book" })
	public List<Comment> findAllForBook(@ShellOption({ "-id_book" }) Long idBook) {
		return commentService.findAllByBookId(idBook);
	}

	@ShellMethod(value = "delete comment", key = { "dc", "delete-comment" })
	public void deleteComment(@ShellOption({ "-id_book" }) Long idBook) {
		this.commentService.delete(idBook);
	}
	
	@ShellMethod(value = "find one comment", key = { "foc", "find-one-comment" })
	public Comment findOneComment(@ShellOption({ "-id_book" }) Long idBook) {
		return this.commentService.findOne(idBook);
	}

}
