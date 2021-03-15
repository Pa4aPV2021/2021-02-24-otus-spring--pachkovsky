package ru.otus.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResultReport;

@ExtendWith(MockitoExtension.class)
public class VerifyingServiceImplTest {

	@Mock
	private QuestionDao questionDao;
	@InjectMocks
	private QuestionServiceImpl questionServiceImpl ;

	List<Question> questions = new ArrayList<Question>(
			Arrays.asList(new Question(1, "", "Молчание"), new Question(2, "", "Труд")));

	List<Answer> blankAnswers = new ArrayList<Answer>(
			Arrays.asList(new Answer(1, "молчание"), new Answer(2, "неправельный ответ")));

	@DisplayName("Коректно проходит требования теста")
	@Test
	void shouldPassRequirementsTest() {
		final int PASSABLE_REQUIREMENTS = 1;
		assertThat(this.checkPassingTestByRequired(PASSABLE_REQUIREMENTS).isTestPassed()).isTrue();

	}

	@DisplayName("Коректно не проходит требования теста")
	@Test
	void shouldFailRequirementsTest() {
		final int NOT_PASSABLE_REQUIREMENTS = 2;
		assertThat(this.checkPassingTestByRequired(NOT_PASSABLE_REQUIREMENTS).isTestPassed()).isFalse();
	}

	private TestResultReport checkPassingTestByRequired(int required) {
		VerifyingServiceImpl verifyingServiceImpl = new VerifyingServiceImpl(required, questionServiceImpl);
		given(questionServiceImpl.getAll()).willReturn(questions);
		return verifyingServiceImpl.checkPassingTest(this.blankAnswers);

	}

}
