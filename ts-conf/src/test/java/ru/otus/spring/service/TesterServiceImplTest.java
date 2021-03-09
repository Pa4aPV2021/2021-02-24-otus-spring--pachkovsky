package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Setting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class TesterServiceImplTest {

	@Mock
	private ConsoleService consoleService;

	@Mock
	private QuestionService questionService;

	@InjectMocks
	private TesterServiceImpl testerServiceImpl;

	@Test
	void test() {

		given(questionService.getAll())
				.willReturn(new ArrayList<Question>(Arrays.asList(new Question(1, "trueAnswer", "trueAnswer"),
						new Question(2, "trueAnswer", "trueAnswer"), new Question(3, "trueAnswer", "trueAnswer"))));
		given(consoleService.requestForInput(any())).willReturn("trueAnswer");
		
		Setting setting	= new Setting();
		
		setting.setRequiredNumberResponses(2);
		
		testerServiceImpl.takeTest(setting);
		
		assertThat(testerServiceImpl.getNumberPointsScored()).isEqualTo(2);
	}

}
