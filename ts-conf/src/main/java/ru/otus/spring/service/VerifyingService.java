package ru.otus.spring.service;

import java.util.List;

import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.TestResultReport;

public interface VerifyingService {
	TestResultReport checkPassingTest(List<Answer> blankAnswers);
}
