package ru.otus.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.otus.spring.domain.Question;
import ru.otus.spring.service.CsvMaper;

@Repository
public class QuestionDaoCsv implements QuestionDao {

	private final CsvMaper csvMaperService;
	private final static String RESOURCE_CSV_PATH = "questions.csv";
	private final static String[] COLUMNS = new String[] { "id", "questionText", "trueAnswer" };
	private final static Class<Question> TO_MAP_TYPE = Question.class;

	public QuestionDaoCsv(CsvMaper csvMaperService) {
		this.csvMaperService = csvMaperService;
	}

	@Override
	public List<Question> findAll() {
		return csvMaperService.csvToObjectsList(RESOURCE_CSV_PATH, COLUMNS, TO_MAP_TYPE);

	}

}
