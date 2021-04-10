package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ru.otus.spring.service.TestingStudentsService;



@SpringBootApplication
public class LocaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocaleApplication.class, args).getBean(TestingStudentsService.class).start();
	}

}

