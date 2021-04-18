package ru.otus.spring.shell.service;

import ru.otus.spring.shell.domain.TestResultReport;
import ru.otus.spring.shell.domain.User;

public interface TestInfoPrinterService {

	void printStartInfo(User authorizedUser);

	void printResultInfo(TestResultReport testResultReport);

}
