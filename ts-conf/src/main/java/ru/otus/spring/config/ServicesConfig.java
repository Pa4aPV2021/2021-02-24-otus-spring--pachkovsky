package ru.otus.spring.config;

import java.io.InputStream;
import java.io.PrintStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

	@Bean
	public PrintStream printStream() {
		return System.out;
	}

	@Bean
	public InputStream inputStream() {
		return System.in;
	}
}