package ru.otus.spring.mongodb.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.mongodb.dao.CommentDao;
import ru.otus.spring.mongodb.domain.Book;
import ru.otus.spring.mongodb.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentDao commentDao;

	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Transactional
	@Override
	public Comment createForBook(String idBook, String textComment) {
		return commentDao.save(new Comment(textComment, new Book(idBook)));
	}

	@Transactional
	@Override
	public Comment update(String idComment, String newTextComment) {
		Comment oldComment = this.findById(idComment);
		oldComment.setText(newTextComment);
		return commentDao.save(oldComment);
	}

	@Transactional(readOnly = true)
	@Override
	public Comment findById(String id) {
		return commentDao.findById(id).orElseThrow(() -> new RuntimeException("Comment: " + id + " not found"));
	}

	@Transactional(readOnly = true)
	@Override
	public List<Comment> findByBook_Id(String idBook) {
		return commentDao.findByBook_Id(idBook);
	}

	@Transactional
	@Override
	public void delete(String id) {
		commentDao.deleteById(id);
	}

}
