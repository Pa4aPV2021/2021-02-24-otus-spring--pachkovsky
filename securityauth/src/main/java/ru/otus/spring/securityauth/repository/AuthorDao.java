package ru.otus.spring.securityauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.securityauth.domain.Author;






public interface AuthorDao extends JpaRepository<Author, Long> {
}
