package ru.otus.spring.shell.service;

import java.util.List;

import ru.otus.spring.shell.domain.Answer;
import ru.otus.spring.shell.domain.TestResultReport;

public interface VerifyingService {
	TestResultReport checkPassingTest(List<Answer> blankAnswers);
}
