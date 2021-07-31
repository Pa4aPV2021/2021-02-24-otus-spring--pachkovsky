package ru.otus.spring.mongodb.service;

import java.util.List;

import ru.otus.spring.mongodb.domain.Comment;

public interface CommentService {

	Comment createForBook(String idBook, String textComment);

	Comment update(String idComment, String newTextComment);

	Comment findById(String id);

	void delete(String id);

	List<Comment> findByBookId(String id_book);

}
