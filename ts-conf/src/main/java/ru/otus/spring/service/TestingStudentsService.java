package ru.otus.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.TestResultReport;
import ru.otus.spring.domain.User;

@Service
public class TestingStudentsService {

	private final AuthorizationService authorizationService;
	private final TesterService consoleTesterService;
	private final ConsoleService consoleService;
	private final VerifyingService verifyingService;

	public TestingStudentsService(AuthorizationService authorizationService, TesterService consoleTesterService,
			ConsoleService consoleService, VerifyingService verifyingService) {
		this.authorizationService = authorizationService;
		this.consoleTesterService = consoleTesterService;
		this.consoleService = consoleService;
		this.verifyingService = verifyingService;

	}

	public void start() {
		authorizationService.authorize();
		consoleService.requestForOutput(createStartTestInfo(authorizationService.getAuthorizedUser()));

		List<Answer> blankAnswers = consoleTesterService.takeTest();
		TestResultReport testResultReport = verifyingService.checkPassingTest(blankAnswers);

		consoleService.requestForOutput(createRezultTestInfo(testResultReport));
	}

	private String createStartTestInfo(User authorizedUser) {
		StringBuilder rezultTestInfo = new StringBuilder("�����������: " + authorizedUser.getName());
		rezultTestInfo.append("\n");
		rezultTestInfo.append("������ �����");
		rezultTestInfo.append("\n");
		return rezultTestInfo.toString();
	}

	private String createRezultTestInfo(TestResultReport testResultReport) {
		StringBuilder rezultTestInfo = new StringBuilder(
				"��������� �����: " + testResultReport.getNumberRequiredAnswers());
		rezultTestInfo.append("\n");
		rezultTestInfo.append("�������� �����: " + testResultReport.getNumber�orrectAnswers());
		rezultTestInfo.append("\n");

		if (testResultReport.isTestPassed()) {
			rezultTestInfo.append("���� �������");
		} else {
			rezultTestInfo.append("���� �� �������");
		}

		return rezultTestInfo.toString();

	}

}
