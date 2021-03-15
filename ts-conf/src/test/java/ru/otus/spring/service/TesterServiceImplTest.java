package ru.otus.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.otus.spring.domain.Question;

@ExtendWith(MockitoExtension.class)
class TesterServiceImplTest {
//
//	@Mock
//	private ConsoleServiceImpl consoleService;
//
//	@Mock
//	private QuestionService questionService;
//
//
//
//	private TesterService testerServiceImpl;
//
//	@Test
//	void test() {
//
//		this.testerServiceImpl = new ConsoleTesterService(4, consoleService, questionService);
//		given(questionService.getAll()).willReturn(new ArrayList<Question>(
//				Arrays.asList(new Question(1, "", "trueAnswer"), new Question(2, "", "falseAnswer"))));
//		given(consoleService.requestForInput(any())).willReturn("trueAnswer");
//
//		testerServiceImpl.takeTest();
//
//		assertThat(testerServiceImpl.getNumberPointsScored()).isEqualTo(1);
//	}
//
}
