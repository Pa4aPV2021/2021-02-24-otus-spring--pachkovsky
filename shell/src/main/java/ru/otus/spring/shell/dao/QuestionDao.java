package ru.otus.spring.shell.dao;

import java.util.List;

import ru.otus.spring.shell.domain.Question;

public interface QuestionDao {

	List<Question> findAll();
}