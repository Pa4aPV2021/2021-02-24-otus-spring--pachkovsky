package ru.otus.spring.securityauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.securityauth.domain.Genre;



public interface GenreDao extends JpaRepository<Genre, Long> {
}
