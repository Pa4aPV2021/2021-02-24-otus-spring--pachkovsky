package ru.otus.spring.shell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.otus.spring.shell.service.OutputInputService;
import ru.otus.spring.shell.service.OutputInputServiceImpl;

@Configuration
public class ServicesConfig {

	@Bean
	public OutputInputService outputInputService() {
		return new OutputInputServiceImpl(System.in, System.out);
	}

}