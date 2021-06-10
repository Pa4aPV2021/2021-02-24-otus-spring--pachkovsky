package ru.otus.spring.ajax.dao;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.ajax.domain.Book;



public interface BookDao extends JpaRepository<Book, Long> {
	@EntityGraph(attributePaths = {"author", "genre"})
	List<Book> findAll();
}
