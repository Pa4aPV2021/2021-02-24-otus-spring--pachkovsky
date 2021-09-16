package ru.otus.spring.jpa.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ru.otus.spring.jpa.domain.Author;

@Repository
public class AuthorDaoJDBC implements AuthorDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Author> findAll() {
		TypedQuery<Author> query = em.createQuery("select id, name from Author a", Author.class);
		return query.getResultList();
	}

	@Override
	public Optional<Author> findOne(Long id) {
		return Optional.ofNullable(em.find(Author.class, id));
	}
}
