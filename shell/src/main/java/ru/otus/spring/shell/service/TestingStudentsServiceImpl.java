package ru.otus.spring.shell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.otus.spring.shell.domain.Answer;
import ru.otus.spring.shell.domain.TestResultReport;
import ru.otus.spring.shell.domain.User;

@Service
public class TestingStudentsServiceImpl implements TestingStudentsService {

	private final AuthorizationService authorizationService;
	private final TesterService testerService;
	private final TestInfoPrinterService testInfoPrinterService;
	private final VerifyingService verifyingService;

	@Autowired
	public TestingStudentsServiceImpl(AuthorizationService authorizationService, TesterService testerService,
			TestInfoPrinterService testInfoPrinterService, VerifyingService verifyingService) {
		super();
		this.authorizationService = authorizationService;
		this.testerService = testerService;
		this.testInfoPrinterService = testInfoPrinterService;
		this.verifyingService = verifyingService;
	}

	@Override
	public void start() {
		User authorizedUser = authorizationService.authorize();
		testInfoPrinterService.printStartInfo(authorizedUser);
		List<Answer> blankAnswers = testerService.takeTest();
		TestResultReport testResultReport = verifyingService.checkPassingTest(blankAnswers);
		testInfoPrinterService.printResultInfo(testResultReport);
	}

}
