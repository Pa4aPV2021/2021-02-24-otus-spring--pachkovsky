package ru.otus.spring.batch.dao.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.batch.domain.entity.CommentEntity;

public interface CommentEntityDao extends JpaRepository<CommentEntity, Long> {
}
