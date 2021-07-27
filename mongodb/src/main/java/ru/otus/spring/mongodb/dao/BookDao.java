package ru.otus.spring.mongodb.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.otus.spring.mongodb.domain.Book;



public interface BookDao extends MongoRepository<Book, String>, BookDaoCustom {

	
}
