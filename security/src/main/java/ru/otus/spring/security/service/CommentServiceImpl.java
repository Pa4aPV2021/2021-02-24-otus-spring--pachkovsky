package ru.otus.spring.security.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ru.otus.spring.security.domain.Book;
import ru.otus.spring.security.domain.Comment;
import ru.otus.spring.security.repository.CommentDao;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentDao commentDao;

	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Transactional
	@Override
	public Comment createForBook(Long idBook, String textComment) {
		return commentDao.save(new Comment(textComment, new Book(idBook)));
	}

	@Transactional
	@Override
	public Comment update(Long idComment, String newTextComment) {
		Comment oldComment = this.findById(idComment);
		oldComment.setText(newTextComment);
		return commentDao.save(oldComment);
	}

	@Transactional(readOnly = true)
	@Override
	public Comment findById(Long id) {
		return commentDao.findById(id).orElseThrow(() -> new RuntimeException("Comment: " + id + " not found"));
	}

	@Transactional(readOnly = true)
	@Override
	public List<Comment> findByBook_Id(Long idBook) {
		return commentDao.findByBook_Id(idBook);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		commentDao.deleteById(id);
	}

}
