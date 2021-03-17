package ru.otus.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

@Service
public class TesterServiceImpl implements TesterService {
	private final OutputInputServiceImpl outputInputService;
	private final QuestionService questionService;

	public TesterServiceImpl(OutputInputServiceImpl outputInputService, QuestionService questionService) {
		this.outputInputService = outputInputService;
		this.questionService = questionService;
	}

	public List<Answer> takeTest() {
		return questionService.getAll().stream()
				.map(question -> createAnswer(question, outputInputService.requestForInput(question.getQuestionText())))
				.collect(Collectors.toList());
	}

	private Answer createAnswer(Question question, String answerText) {
		Answer newAnswer = new Answer();
		newAnswer.setQuestion(question);
		newAnswer.setAnswerText(answerText);
		return newAnswer;
	}

}
