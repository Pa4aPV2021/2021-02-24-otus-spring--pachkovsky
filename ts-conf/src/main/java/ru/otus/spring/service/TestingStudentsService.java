package ru.otus.spring.service;

import org.springframework.stereotype.Service;

import ru.otus.spring.domain.Setting;
import ru.otus.spring.domain.User;

@Service
public class TestingStudentsService {

	private final AuthorizationService authorizationService;
	private final TesterService testerService;
	private final ConsoleService consoleService;
	private final SettingService settingService;

	public TestingStudentsService(AuthorizationService authorizationService, TesterService testerService,
			ConsoleService consoleService, SettingService settingService) {
		super();

		this.authorizationService = authorizationService;
		this.testerService = testerService;
		this.consoleService = consoleService;
		this.settingService = settingService;

	}

	public void start() {
		authorizationService.authorize();
		Setting settingTest = settingService.findByLevelName(TestLvl.SIMPLE.name());
		consoleService.requestForOutput(createStartTestInfo(settingTest, authorizationService.getAuthorizedUser()));
		consoleService.requestForOutput(createRezultTestInfo(testerService.takeTest(settingTest), settingTest));
	}

	private String createStartTestInfo(Setting settingTest, User authorizedUser) {
		StringBuilder rezultTestInfo = new StringBuilder("Тестируемый: " + authorizedUser.getName());
		rezultTestInfo.append("\n");
		rezultTestInfo.append("Уровень сложности:" + settingTest.getLevelName());
		rezultTestInfo.append("\n");
		rezultTestInfo.append("Требуемое количество балов: " + settingTest.getRequiredNumberResponses());
		rezultTestInfo.append("\n");
		rezultTestInfo.append("Начало теста");
		rezultTestInfo.append("\n");
		return rezultTestInfo.toString();
	}

	private String createRezultTestInfo(boolean isTakeTest, Setting settingTest) {
		StringBuilder rezultTestInfo = new StringBuilder("Набранно балов: " + testerService.getNumberPointsScored());
		rezultTestInfo.append("\n");

		if (isTakeTest) {
			rezultTestInfo.append("Тест пройден");
			rezultTestInfo.append("\n");
			rezultTestInfo.append("Ваша награда: " + settingTest.getReward());
		} else {
			rezultTestInfo.append("Тест не пройден");
		}

		return rezultTestInfo.toString();

	}

}
