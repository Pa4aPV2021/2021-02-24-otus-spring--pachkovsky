package ru.otus.spring.springdata.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.springdata.domain.Author;


public interface AuthorDao extends JpaRepository<Author, Long> {
}
