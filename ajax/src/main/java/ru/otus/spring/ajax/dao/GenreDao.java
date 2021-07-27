package ru.otus.spring.ajax.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.ajax.domain.Genre;

public interface GenreDao extends JpaRepository<Genre, Long> {
}
