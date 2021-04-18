package ru.otus.spring.shell;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.otus.spring.shell.domain.Answer;
import ru.otus.spring.shell.domain.Question;
import ru.otus.spring.shell.domain.TestResultReport;
import ru.otus.spring.shell.service.VerifyingService;
import ru.otus.spring.shell.service.VerifyingServiceImpl;

public class VerifyingServiceImplTest {

	List<Answer> blankAnswers = new ArrayList<Answer>(
			Arrays.asList(new Answer(new Question(1, "", "Молчание"), "молчание"),
					new Answer(new Question(2, "", "Труд"), "неправельный ответ")));

	@DisplayName("Корректно проходит требования теста")
	@Test
	void shouldPassRequirementsTest() {
		final int PASSABLE_REQUIREMENTS = 1;
		assertThat(this.checkPassingTestByRequired(PASSABLE_REQUIREMENTS).isTestPassed()).isTrue();

	}

	@DisplayName("Корректно не проходит требования теста")
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
