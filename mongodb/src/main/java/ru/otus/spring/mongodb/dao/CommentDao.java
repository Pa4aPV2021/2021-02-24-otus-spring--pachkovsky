package ru.otus.spring.mongodb.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import ru.otus.spring.mongodb.domain.Comment;

public interface CommentDao extends MongoRepository<Comment, String> {

	List<Comment> findByBook_Id(String id);

}
