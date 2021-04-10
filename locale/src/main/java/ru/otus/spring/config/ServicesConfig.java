package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.otus.spring.service.OutputInputService;
import ru.otus.spring.service.OutputInputServiceImpl;

@Configuration
public class ServicesConfig {

	@Bean
	public OutputInputService outputInputService() {
		return new OutputInputServiceImpl(System.in, System.out);
	}

}