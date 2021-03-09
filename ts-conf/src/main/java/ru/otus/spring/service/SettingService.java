package ru.otus.spring.service;
import ru.otus.spring.domain.Setting;

public interface SettingService {
	Setting findByLevelName(String levelName);
}