package ru.otus.spring.securityauth.repository;


import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.securityauth.domain.Book;

public interface BookDao extends JpaRepository<Book, Long> {
	@EntityGraph(attributePaths = {"author", "genre"})
	List<Book> findAll();
}
