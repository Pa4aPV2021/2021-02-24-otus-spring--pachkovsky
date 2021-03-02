package ru.otus.spring.service;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

import ru.otus.spring.domain.Question;

public class QuestionServiceImpl implements QuestionService {

	private final CsvReader csvReader;

	public QuestionServiceImpl(CsvReader csvReader) {
		this.csvReader = csvReader;
	}

	public List<Question> getQuestionsFromDefaultCsvFile() throws FileNotFoundException, URISyntaxException {
		return csvReader.readDefaultCsvFile(new String[] { "id", "question_text", "true_answer" }, Question.class);
	}

}