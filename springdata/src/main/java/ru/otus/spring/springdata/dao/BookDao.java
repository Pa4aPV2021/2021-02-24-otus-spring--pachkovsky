package ru.otus.spring.springdata.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.springdata.domain.Book;



public interface BookDao extends JpaRepository<Book, Long> {
}
