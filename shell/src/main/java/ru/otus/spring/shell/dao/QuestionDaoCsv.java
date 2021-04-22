package ru.otus.spring.shell.dao;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import ru.otus.spring.shell.domain.Question;
import ru.otus.spring.shell.service.CsvMaper;
import ru.otus.spring.shell.service.LocalProvider;

@Repository
public class QuestionDaoCsv implements QuestionDao {

	private final CsvMaper csvMaperService;
	private final static String[] COLUMNS = new String[] { "id", "questionText", "trueAnswer" };
	private final static Class<Question> TO_MAP_TYPE = Question.class;
	private final String questionsCsvPathLocaleFormat;

	public QuestionDaoCsv(@Value("${questions-csv-root-path}") String questionsCsvRootPath, CsvMaper csvMaperService,
			LocalProvider localProvider) {
		Locale locale = localProvider.getLocal();
		this.csvMaperService = csvMaperService;
		this.questionsCsvPathLocaleFormat = questionsCsvRootPath + "_" + locale.getCountry() + "-"
				+ locale.getLanguage() + ".csv";
	}

	@Override
	public List<Question> findAll() {
		return csvMaperService.csvToObjectsList(questionsCsvPathLocaleFormat, COLUMNS, TO_MAP_TYPE);
	}

}
