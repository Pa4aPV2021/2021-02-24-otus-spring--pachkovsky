package ru.otus.spring.thymeleaf.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.thymeleaf.domain.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {

	List<Comment> findByBook_Id(Long id);

}
