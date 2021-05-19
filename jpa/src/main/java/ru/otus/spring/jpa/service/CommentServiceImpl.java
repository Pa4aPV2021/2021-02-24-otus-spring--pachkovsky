package ru.otus.spring.jpa.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.jpa.dao.CommentDao;
import ru.otus.spring.jpa.domain.Book;
import ru.otus.spring.jpa.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentDao commentDao;

	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Transactional
	@Override
	public Comment createForBook(Long idBook, String textComment) {
		return commentDao.create(new Comment(textComment, new Book(idBook)));
	}

	@Transactional
	@Override
	public Comment update(Long idComment, String newTextComment) {
		Comment oldComment = this.findOne(idComment);
		oldComment.setText(newTextComment);
		return commentDao.update(oldComment);
	}

	@Transactional(readOnly = true)
	@Override
	public Comment findOne(Long id) {
		return commentDao.findOne(id).orElseThrow(() -> new RuntimeException("Comment: " + id + " not found"));
	}

	@Transactional(readOnly = true)
	@Override
	public List<Comment> findAllByBookId(Long idBook) {
		return commentDao.findAllForBook(idBook);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		commentDao.delete(id);
	}

}
