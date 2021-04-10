package ru.otus.spring.service;

import ru.otus.spring.domain.TestResultReport;
import ru.otus.spring.domain.User;

public interface TestInfoPrinterService {

	public void createStartInfo(User authorizedUser);

	public void createResultInfo(TestResultReport testResultReport);

}
