package ru.otus.spring.service;

import org.springframework.stereotype.Service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Setting;

@Service
public class TesterServiceImpl implements TesterService {

	private final ConsoleService consoleService;
	private final QuestionService questionService;
	private int numberPointsScored;

	public int getNumberPointsScored() {
		return numberPointsScored;
	}

	public TesterServiceImpl(ConsoleService consoleService, QuestionService questionService) {
		this.consoleService = consoleService;
		this.questionService = questionService;
	}

	public boolean takeTest(Setting settingTest) {
		this.numberPointsScored = 0;
		for (Question question : questionService.getAll()) {
			if (question.getTrueAnswer().toLowerCase()
					.equals(consoleService.requestForInput(question.getQuestionText()).toLowerCase())) {
				this.numberPointsScored++;
			}
			;
		}
		return this.numberPointsScored >= settingTest.getRequiredNumberResponses();
	}

}
