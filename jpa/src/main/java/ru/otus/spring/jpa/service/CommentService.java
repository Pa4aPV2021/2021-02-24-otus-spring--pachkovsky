package ru.otus.spring.jpa.service;

import java.util.List;

import ru.otus.spring.jpa.domain.Comment;

public interface CommentService {

	Comment createForBook(Long idBook, String textComment);

	Comment update(Long idComment, String newTextComment);

	Comment findOne(Long id);

	void delete(Long id);

	List<Comment> findAllByBookId(Long id_book);

}
