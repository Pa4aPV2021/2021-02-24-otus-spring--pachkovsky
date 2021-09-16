package ru.otus.spring.jpa.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ru.otus.spring.jpa.domain.Comment;

@Repository
public class CommentDaoJPA implements CommentDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Comment create(Comment createdBook) {
		em.persist(createdBook);
		return createdBook;
	}

	@Override
	public void delete(Long id) {
		Query query = em.createQuery("delete from Comment c where c.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public Optional<Comment> findOne(Long id) {
		return Optional.ofNullable(em.find(Comment.class, id));
	}

	@Override
	public List<Comment> findAllForBook(Long idBook) {
		TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book.id = :idBook", Comment.class);
		query.setParameter("idBook", idBook);
		return query.getResultList();
	}

	@Override
	public Comment update(Comment updatedComment) {
		return em.merge(updatedComment);
	}

}
