package ru.otus.spring.batch.dao.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.batch.domain.entity.AuthorEntity;

public interface AuthorEntityDao extends JpaRepository<AuthorEntity, Long> {
}