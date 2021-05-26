package ru.otus.spring.springdata.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.springdata.domain.Genre;

public interface GenreDao extends JpaRepository<Genre, Long> {
}
