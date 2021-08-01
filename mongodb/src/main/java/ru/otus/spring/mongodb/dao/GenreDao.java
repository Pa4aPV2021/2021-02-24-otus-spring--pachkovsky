package ru.otus.spring.mongodb.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.otus.spring.mongodb.domain.Genre;

public interface GenreDao extends MongoRepository<Genre, String> {
}
