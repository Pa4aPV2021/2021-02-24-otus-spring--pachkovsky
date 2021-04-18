package ru.otus.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.TestResultReport;
import ru.otus.spring.domain.User;

@Service
public class TestingStudentsService {

	private final AuthorizationService authorizationService;
	private final TesterService testerService;
	private final TestInfoPrinterService testInfoPrinterService;
	private final VerifyingService verifyingService;

	public TestingStudentsService(AuthorizationService authorizationService, TesterService testerService,
			TestInfoPrinterService testInfoPrinterService, VerifyingService verifyingService) {
		this.authorizationService = authorizationService;
		this.testerService = testerService;
		this.testInfoPrinterService = testInfoPrinterService;
		this.verifyingService = verifyingService;
	}

	public void start() {
		User authorizedUser = authorizationService.authorize();
		testInfoPrinterService.createStartInfo(authorizedUser);
		List<Answer> blankAnswers = testerService.takeTest();
		TestResultReport testResultReport = verifyingService.checkPassingTest(blankAnswers);
		testInfoPrinterService.createResultInfo(testResultReport);
	}
}
