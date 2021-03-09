package ru.otus.spring.dao;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.stereotype.Repository;

import ru.otus.spring.domain.Setting;
import ru.otus.spring.service.CsvMaperService;

@Repository
public class SettingDaoCsv implements SettingDao {

	private CsvMaperService csvMaperService;
	private final static String RESURS_CSV_PATH = "setting.csv";
	private final static String[] COLUMNS = new String[] { "id", "levelName", "requiredNumberResponses", "reward" };
	private final static Class<Setting> TO_MAP_TYPE = Setting.class;

	public SettingDaoCsv(CsvMaperService csvMaperService) {
		super();
		this.csvMaperService = csvMaperService;
	}

	private List<Setting> findAll() {
		List<Setting> resultQuestions = null;
		try {
			resultQuestions = csvMaperService.rowToObject(RESURS_CSV_PATH, COLUMNS, TO_MAP_TYPE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultQuestions;

	}

	@Override
	public Setting findByLevelName(String levelName) {
		return this.findAll().stream()
				.filter(question -> question.getLevelName().toLowerCase().equals(levelName.toLowerCase())).findFirst()
				.get();
	}

}