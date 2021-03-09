package ru.otus.spring.dao;

import ru.otus.spring.domain.Setting;

public interface SettingDao {
	Setting findByLevelName(String levelName);
}
