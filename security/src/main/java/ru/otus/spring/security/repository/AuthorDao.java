package ru.otus.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.security.domain.Author;




public interface AuthorDao extends JpaRepository<Author, Long> {
}
