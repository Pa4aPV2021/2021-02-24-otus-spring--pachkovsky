package ru.otus.spring.security;

import java.sql.SQLException;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(SecurityApplication.class, args);
		Console.main(args);
	}

}
