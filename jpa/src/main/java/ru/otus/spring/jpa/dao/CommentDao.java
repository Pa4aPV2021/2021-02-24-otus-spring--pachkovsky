package ru.otus.spring.jpa.dao;

import java.util.List;
import java.util.Optional;

import ru.otus.spring.jpa.domain.Comment;

public interface CommentDao {

	Comment create(Comment createdComent);
	
	Comment update(Comment updatedComment);

	void delete(Long id);

	Optional<Comment> findOne(Long id);

	List<Comment> findAllForBook(Long id);

}
