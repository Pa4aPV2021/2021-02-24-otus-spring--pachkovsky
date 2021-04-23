package ru.otus.spring.jdbc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.otus.spring.jdbc.service.OutputInputService;
import ru.otus.spring.jdbc.service.OutputInputServiceImpl;

@Configuration
public class ServicesConfig {

	@Bean
	public OutputInputService outputInputService() {
		return new OutputInputServiceImpl(System.in, System.out);
	}

}