package ru.otus.spring.service;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;
import ru.otus.spring.domain.Question;

public interface QuestionService {
	List<Question> getQuestionsFromDefaultCsvFile() throws FileNotFoundException, URISyntaxException;
}