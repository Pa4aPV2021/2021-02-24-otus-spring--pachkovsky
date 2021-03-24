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
	private final OutputInputService outputInputService;
	private final VerifyingService verifyingService;
	private final MessageSourceLocalized messageSourceLocalized;

	public TestingStudentsService(AuthorizationService authorizationService, TesterService testerService,
			OutputInputService outputInputService, VerifyingService verifyingService,
			MessageSourceLocalized messageSourceLocalized) {
		this.authorizationService = authorizationService;
		this.testerService = testerService;
		this.outputInputService = outputInputService;
		this.verifyingService = verifyingService;
		this.messageSourceLocalized = messageSourceLocalized;

	}

	public void start() {
		User authorizedUser = authorizationService.authorize();
		outputInputService.requestForOutput(createStartTestInfo(authorizedUser));
		List<Answer> blankAnswers = testerService.takeTest();
		TestResultReport testResultReport = verifyingService.checkPassingTest(blankAnswers);
		outputInputService.requestForOutput(createResultTestInfo(testResultReport));
	}

	private String createStartTestInfo(User authorizedUser) {
		StringBuilder rezultTestInfo = new StringBuilder(
				messageSourceLocalized.getMessage("start-info.tested", authorizedUser.getName()));
		rezultTestInfo.append("\n");
		rezultTestInfo.append(messageSourceLocalized.getMessage("start-info.start"));
		rezultTestInfo.append("\n");
		return rezultTestInfo.toString();
	}

	private String createResultTestInfo(TestResultReport testResultReport) {
		StringBuilder rezultTestInfo = new StringBuilder(messageSourceLocalized.getMessage("rezult-info.required",
				String.valueOf(testResultReport.getNumberRequiredAnswers())));
		rezultTestInfo.append("\n");
		rezultTestInfo.append(messageSourceLocalized.getMessage("rezult-info.scored",
				String.valueOf(testResultReport.getNumberСorrectAnswers())));
		rezultTestInfo.append("\n");

		if (testResultReport.isTestPassed()) {
			rezultTestInfo.append(messageSourceLocalized.getMessage("rezult-info.success"));
		} else {
			rezultTestInfo.append(messageSourceLocalized.getMessage("rezult-info.failed"));
		}

		return rezultTestInfo.toString();

	}

}
