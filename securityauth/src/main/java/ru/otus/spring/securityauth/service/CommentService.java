package ru.otus.spring.securityauth.service;

import java.util.List;

import ru.otus.spring.securityauth.domain.Comment;

public interface CommentService {

	Comment createForBook(Long idBook, String textComment);

	Comment update(Long idComment, String newTextComment);

	Comment findById(Long id);

	void delete(Long id);

	List<Comment> findByBook_Id(Long id_book);

}
