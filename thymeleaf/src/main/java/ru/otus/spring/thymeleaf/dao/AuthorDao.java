package ru.otus.spring.thymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.thymeleaf.domain.Author;


public interface AuthorDao extends JpaRepository<Author, Long> {
}
