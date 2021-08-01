package ru.otus.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.security.domain.Genre;



public interface GenreDao extends JpaRepository<Genre, Long> {
}
