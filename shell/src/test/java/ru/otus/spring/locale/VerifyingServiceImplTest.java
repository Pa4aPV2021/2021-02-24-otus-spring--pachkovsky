package ru.otus.spring.locale;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResultReport;
import ru.otus.spring.service.VerifyingService;
import ru.otus.spring.service.VerifyingServiceImpl;

@SpringBootTest
public class VerifyingServiceImplTest {

	List<Answer> blankAnswers = new ArrayList<Answer>(
			Arrays.asList(new Answer(new Question(1, "", "Молчание"), "молчание"),
					new Answer(new Question(2, "", "Труд"), "неправельный ответ")));

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
		VerifyingService verifyingServiceImpl = new VerifyingServiceImpl(required); // вы говорите без new, тогда не
																					// понимаю как сделать тесты с двумя
																					// разными требованиями
		return verifyingServiceImpl.checkPassingTest(this.blankAnswers);
	}

}
