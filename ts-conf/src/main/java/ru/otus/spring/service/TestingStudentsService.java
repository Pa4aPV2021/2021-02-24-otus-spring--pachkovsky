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
		StringBuilder rezultTestInfo = new StringBuilder("�����������: " + authorizedUser.getName());
		rezultTestInfo.append("\n");
		rezultTestInfo.append("������� ���������:" + settingTest.getLevelName());
		rezultTestInfo.append("\n");
		rezultTestInfo.append("��������� ���������� �����: " + settingTest.getRequiredNumberResponses());
		rezultTestInfo.append("\n");
		rezultTestInfo.append("������ �����");
		rezultTestInfo.append("\n");
		return rezultTestInfo.toString();
	}

	private String createRezultTestInfo(boolean isTakeTest, Setting settingTest) {
		StringBuilder rezultTestInfo = new StringBuilder("�������� �����: " + testerService.getNumberPointsScored());
		rezultTestInfo.append("\n");

		if (isTakeTest) {
			rezultTestInfo.append("���� �������");
			rezultTestInfo.append("\n");
			rezultTestInfo.append("���� �������: " + settingTest.getReward());
		} else {
			rezultTestInfo.append("���� �� �������");
		}

		return rezultTestInfo.toString();

	}

}
