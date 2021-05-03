package ru.otus.spring.jpa.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ru.otus.spring.jpa.domain.Book;

@Transactional
@Repository
public class BookDaoJPA implements BookDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Book create(Book createdBook) {
		em.persist(createdBook);
		System.out.println(createdBook.getId());
		return createdBook;
	}

	@Override
	public Book update(Book updatedBook) {
		return em.merge(updatedBook);
	}

	@Override
	public List<Book> findAll() {
		TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.author join fetch b.genre",
				Book.class);
		return query.getResultList();
	}

	@Override
	public void delete(Long id) {
		Query query = em.createQuery("delete from Book b where b.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
		em.clear();
	}

	@Override
	public Optional<Book> findOne(Long id) {
		return Optional.ofNullable(em.find(Book.class, id));
	}

}
