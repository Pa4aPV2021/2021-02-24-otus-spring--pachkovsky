package ru.otus.spring.jpa;

import java.sql.SQLException;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(JpaApplication.class, args);
		 Console.main(args);
	}

}
