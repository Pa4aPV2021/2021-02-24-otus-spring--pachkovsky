package ru.otus.spring.springdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.springdata.domain.Book;



public interface BookDao extends JpaRepository<Book, Long> {
	@EntityGraph(attributePaths = {"author", "genre"})
	List<Book> findAll();
}
