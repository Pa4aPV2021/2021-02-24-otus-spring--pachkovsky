package ru.otus.spring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import ru.otus.spring.domain.Question;
import ru.otus.spring.service.CsvMaper;

@Repository
public class QuestionDaoCsv implements QuestionDao {

	private final CsvMaper csvMaperService;
	private final static String[] COLUMNS = new String[] { "id", "questionText", "trueAnswer" };
	private final static Class<Question> TO_MAP_TYPE = Question.class;
	private final String questionsCsvPath;

	public QuestionDaoCsv(@Value("${questions.csv.path}") String questionsCsvPath, CsvMaper csvMaperService) {
		this.csvMaperService = csvMaperService;
		this.questionsCsvPath = questionsCsvPath;
	}

	@Override
	public List<Question> findAll() {
		return csvMaperService.csvToObjectsList(questionsCsvPath, COLUMNS, TO_MAP_TYPE);

	}

}
