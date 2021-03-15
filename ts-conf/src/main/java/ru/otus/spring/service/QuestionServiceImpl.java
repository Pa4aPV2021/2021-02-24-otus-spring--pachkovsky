package ru.otus.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

@Service
public class QuestionServiceImpl implements QuestionService {

	private final QuestionDao questionDao;

	public QuestionServiceImpl(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public List<Question> getAll() {
		return questionDao.findAll();
	}

	@Override
	public Question findById(int id) {
		return this.getAll().stream().filter(question -> question.getId() == id).findFirst().get();
	}

}