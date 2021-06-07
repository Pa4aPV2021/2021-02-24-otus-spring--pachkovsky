package ru.otus.spring.thymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.thymeleaf.domain.Genre;

public interface GenreDao extends JpaRepository<Genre, Long> {
}
