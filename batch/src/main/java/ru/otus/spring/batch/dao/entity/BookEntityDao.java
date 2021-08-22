package ru.otus.spring.batch.dao.entity;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.batch.domain.entity.BookEntity;

public interface BookEntityDao extends JpaRepository<BookEntity, Long> {
	@EntityGraph(attributePaths = {"author", "genre"})
	List<BookEntity> findAll();
}
