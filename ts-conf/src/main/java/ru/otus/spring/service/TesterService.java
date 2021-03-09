package ru.otus.spring.service;

import ru.otus.spring.domain.Setting;

public interface TesterService {
	boolean takeTest(Setting settingTest);
	int getNumberPointsScored();

}
