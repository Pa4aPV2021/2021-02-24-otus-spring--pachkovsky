package ru.otus.spring.shell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ru.otus.spring.shell.domain.Answer;
import ru.otus.spring.shell.domain.Question;
import ru.otus.spring.shell.domain.TestResultReport;

@Service
public class VerifyingServiceImpl implements VerifyingService {
	private final int requiredNumberResponses;

	public VerifyingServiceImpl(@Value("${required-number-responses}") int requiredNumberResponses) {
		this.requiredNumberResponses = requiredNumberResponses;
	}

	@Override
	public TestResultReport checkPassingTest(List<Answer> blankAnswers) {
		int numberСorrectAnswers = this.countСorrectAnswers(blankAnswers);
		boolean isTestPassed = this.checkRequirements(numberСorrectAnswers);
		return createNewTestResultReport(numberСorrectAnswers, this.requiredNumberResponses, isTestPassed);
	}

	private TestResultReport createNewTestResultReport(int numberСorrectAnswers, int requiredNumberResponses,
			boolean isTestPassed) {
		TestResultReport newTestResultReport = new TestResultReport();
		newTestResultReport.setNumberRequiredAnswers(requiredNumberResponses);
		newTestResultReport.setNumberСorrectAnswers(numberСorrectAnswers);
		newTestResultReport.setTestPassed(isTestPassed);
		return newTestResultReport;
	}

	private boolean checkRequirements(int numberСorrectAnswers) {
		return numberСorrectAnswers >= this.requiredNumberResponses;
	}

	private int countСorrectAnswers(List<Answer> blankAnswers) {
		int numberСorrectAnswers = 0;
		for (Answer answer : blankAnswers) {
			Question question = answer.getQuestion();
			if (answer.getAnswerText().toLowerCase().equals(question.getTrueAnswer().toLowerCase())) {
				numberСorrectAnswers++;
			}

		}
		return numberСorrectAnswers;
	}

}
