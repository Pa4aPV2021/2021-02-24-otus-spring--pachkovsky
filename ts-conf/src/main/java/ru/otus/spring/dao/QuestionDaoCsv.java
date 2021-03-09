package ru.otus.spring.dao;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.stereotype.Repository;

import ru.otus.spring.domain.Question;
import ru.otus.spring.service.CsvMaperService;

@Repository
public class QuestionDaoCsv implements QuestionDao {

	private CsvMaperService csvMaperService;
	private final static String RESURS_CSV_PATH = "questions.csv";
	private final static String[] COLUMNS = new String[] { "id", "questionText", "trueAnswer" };
	private final static Class<Question> TO_MAP_TYPE = Question.class;

	public QuestionDaoCsv(CsvMaperService csvMaperService) {
		super();
		this.csvMaperService = csvMaperService;
	}

	@Override
	public List<Question> findAll() {
		List<Question> resultQuestions = null;
	
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


}
