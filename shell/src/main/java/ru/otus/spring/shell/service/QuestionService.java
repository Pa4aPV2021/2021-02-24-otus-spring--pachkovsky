package ru.otus.spring.shell.service;

import java.util.List;

import ru.otus.spring.shell.domain.Question;

public interface QuestionService {
	List<Question> getAll();
}