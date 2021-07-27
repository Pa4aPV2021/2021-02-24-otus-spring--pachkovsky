package ru.otus.spring.mongodb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.mongodb.dao.CommentDao;
import ru.otus.spring.mongodb.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentDao commentDao;
	private final BookService bookService;

	public CommentServiceImpl(CommentDao commentDao, BookService bookService) {
		this.commentDao = commentDao;
		this.bookService = bookService;
	}

	@Transactional
	@Override
	public Comment createForBook(String idBook, String textComment) {
		return commentDao.save(new Comment(textComment, bookService.findById(idBook)));
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
