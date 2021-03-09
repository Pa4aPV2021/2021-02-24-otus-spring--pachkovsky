package ru.otus.spring.service;

import org.springframework.stereotype.Service;

import ru.otus.spring.dao.SettingDao;
import ru.otus.spring.domain.Setting;

@Service
public class SettingServiceImpl implements SettingService {

	private final SettingDao settingDao;

	public SettingServiceImpl(SettingDao settingDao) {
		this.settingDao = settingDao;
	}

	@Override
	public Setting findByLevelName(String levelName) {
		return settingDao.findByLevelName(levelName);
	}

}