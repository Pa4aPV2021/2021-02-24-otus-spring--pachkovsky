package ru.otus.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResultReport;

@Service
public class VerifyingServiceImpl implements VerifyingService {
	private final int requiredNumberResponses;

	public VerifyingServiceImpl(@Value("${required.number.responses}") int requiredNumberResponses) {
		this.requiredNumberResponses = requiredNumberResponses;
	}

	@Override
	public TestResultReport checkPassingTest(List<Answer> blankAnswers) {
		int number—orrectAnswers = this.count—orrectAnswers(blankAnswers);
		boolean isTestPassed = this.checkRequirements(number—orrectAnswers);
		return createNewTestResultReport(number—orrectAnswers, this.requiredNumberResponses, isTestPassed);
	}

	private TestResultReport createNewTestResultReport(int number—orrectAnswers, int requiredNumberResponses,
			boolean isTestPassed) {
		TestResultReport newTestResultReport = new TestResultReport();
		newTestResultReport.setNumberRequiredAnswers(requiredNumberResponses);
		newTestResultReport.setNumber—orrectAnswers(number—orrectAnswers);
		newTestResultReport.setTestPassed(isTestPassed);
		return newTestResultReport;
	}

	private boolean checkRequirements(int number—orrectAnswers) {
		return number—orrectAnswers >= this.requiredNumberResponses;
	}

	private int count—orrectAnswers(List<Answer> blankAnswers) {
		int number—orrectAnswers = 0;
		for (Answer answer : blankAnswers) {
			Question question = answer.getQuestion();
			if (answer.getAnswerText().toLowerCase().equals(question.getTrueAnswer().toLowerCase())) {
				number—orrectAnswers++;
			}

		}
		return number—orrectAnswers;
	}

}
