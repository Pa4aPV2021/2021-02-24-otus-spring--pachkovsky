package ru.otus.spring.jpa.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ru.otus.spring.jpa.domain.Genre;

@Repository
public class GenreDaoJPA implements GenreDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Genre> findAll() {
		TypedQuery<Genre> query = em.createQuery("select id, name from Genre g", Genre.class);
		return query.getResultList();
	}

	@Override
	public Optional<Genre> findOne(Long id) {		
		return Optional.ofNullable(em.find(Genre.class, id));
	}

}
