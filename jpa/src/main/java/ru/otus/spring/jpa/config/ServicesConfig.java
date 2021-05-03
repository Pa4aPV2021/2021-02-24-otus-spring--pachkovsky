package ru.otus.spring.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.otus.spring.jpa.service.OutputInputService;
import ru.otus.spring.jpa.service.OutputInputServiceImpl;

@Configuration
public class ServicesConfig {

	@Bean
	public OutputInputService outputInputService() {
		return new OutputInputServiceImpl(System.in, System.out);
	}

}