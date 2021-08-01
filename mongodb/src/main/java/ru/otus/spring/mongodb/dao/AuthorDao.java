package ru.otus.spring.mongodb.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.otus.spring.mongodb.domain.Author;


public interface AuthorDao extends MongoRepository<Author, String> {
}
