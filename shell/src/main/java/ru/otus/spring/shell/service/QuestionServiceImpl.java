package ru.otus.spring.shell.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.otus.spring.shell.dao.QuestionDao;
import ru.otus.spring.shell.domain.Question;

@Service
public class QuestionServiceImpl implements QuestionService {

	private final QuestionDao questionDao;

	public QuestionServiceImpl(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public List<Question> getAll() {
		return questionDao.findAll();
	}

}