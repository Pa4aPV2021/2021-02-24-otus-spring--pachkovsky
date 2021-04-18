package ru.otus.spring.shell;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.spring.shell.domain.Answer;
import ru.otus.spring.shell.domain.Question;
import ru.otus.spring.shell.service.OutputInputService;
import ru.otus.spring.shell.service.QuestionService;
import ru.otus.spring.shell.service.TesterService;

@SpringBootTest
public class TesterServiceImplTest {

	private final static String SUGGESTED_ANSWER = "предложенный-ответ";

	private final static List<Answer> EXPECTED_ANSWERS = new ArrayList<Answer>(
			Arrays.asList(new Answer(new Question(1, "текст-вопроса", "правильный-ответ"), SUGGESTED_ANSWER)));

	private final static List<Question> QUESTIONS = new ArrayList<Question>(
			Arrays.asList(new Question(1, "текст-вопроса-1", "правильный-ответ-1")));

	@MockBean
	private OutputInputService outputInputService;
	@MockBean
	private QuestionService questionService;

	@Autowired
	private TesterService testerService;

	@DisplayName("Корректно создает лист ответов")
	@Test
	void shouldСreateAnswerSheetCorrectly() {

		given(questionService.getAll()).willReturn(QUESTIONS);
		given(outputInputService.requestForInput(any())).willReturn(SUGGESTED_ANSWER);
		assertThat(testerService.takeTest()).isEqualTo(EXPECTED_ANSWERS);

	}

}