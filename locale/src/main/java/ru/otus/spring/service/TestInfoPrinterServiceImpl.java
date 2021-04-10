package ru.otus.spring.service;

import org.springframework.stereotype.Service;

import ru.otus.spring.domain.TestResultReport;
import ru.otus.spring.domain.User;

@Service
public class TestInfoPrinterServiceImpl implements TestInfoPrinterService {

	private final OutputInputService outputInputService;
	private final MessageSourceLocalized messageSourceLocalized;

	public TestInfoPrinterServiceImpl(OutputInputService outputInputService,
			MessageSourceLocalized messageSourceLocalized) {
		this.outputInputService = outputInputService;
		this.messageSourceLocalized = messageSourceLocalized;

	}

	public void createStartInfo(User authorizedUser) {
		StringBuilder createTestInfo = new StringBuilder(
				messageSourceLocalized.getMessage("start-info.tested", authorizedUser.getName()));
		createTestInfo.append(messageSourceLocalized.getMessage("start-info.start"));
		outputInputService.requestForOutput(createTestInfo.toString());
	}

	public void createResultInfo(TestResultReport testResultReport) {
		StringBuilder resultTestInfo = new StringBuilder(messageSourceLocalized.getMessage("result-info.required",
				String.valueOf(testResultReport.getNumberRequiredAnswers())));
		resultTestInfo.append(messageSourceLocalized.getMessage("result-info.scored",
				String.valueOf(testResultReport.getNumberСorrectAnswers())));

		if (testResultReport.isTestPassed()) {
			resultTestInfo.append(messageSourceLocalized.getMessage("result-info.success"));
		} else {
			resultTestInfo.append(messageSourceLocalized.getMessage("result-info.failed"));
		}

		outputInputService.requestForOutput(resultTestInfo.toString());

	}

}
