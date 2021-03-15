package ru.otus.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ru.otus.spring.domain.Answer;

@Service
public class ConsoleTesterService implements TesterService {
	private final ConsoleServiceImpl consoleService;
	private final QuestionService questionService;

	public ConsoleTesterService(ConsoleServiceImpl consoleService, QuestionService questionService) {
		this.consoleService = consoleService;
		this.questionService = questionService;
	}

	public List<Answer> takeTest() {
//		List<Answer> blankAnswers = new ArrayList<Answer>();
//		
//		
//
//		for (Question question : questionService.getAll()) {
//			blankAnswers
//					.add(createAnswer(question.getId(), consoleService.requestForInput(question.getQuestionText())));
//
//			if (question.getTrueAnswer().toLowerCase()
//					.equals(consoleService.requestForInput(question.getQuestionText()).toLowerCase())) {
//				numberPointsScored++;
//			}
//			;
//		}

		return questionService.getAll().stream().map(
				question -> createAnswer(question.getId(), consoleService.requestForInput(question.getQuestionText())))
				.collect(Collectors.toList());
	}

	private Answer createAnswer(int idQuestion, String answerText) {
		Answer newAnswer = new Answer();
		newAnswer.setIdQuestion(idQuestion);
		newAnswer.setAnswerText(answerText);
		return newAnswer;
	}

}
