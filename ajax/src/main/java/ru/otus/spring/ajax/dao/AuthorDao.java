package ru.otus.spring.ajax.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.ajax.domain.Author;


public interface AuthorDao extends JpaRepository<Author, Long> {
}
