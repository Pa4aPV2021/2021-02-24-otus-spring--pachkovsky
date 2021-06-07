package ru.otus.spring.thymeleaf;

import java.sql.SQLException;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TymeleafApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(TymeleafApplication.class, args);
		 Console.main(args);
	}

}
