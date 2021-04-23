package ru.otus.spring.jdbc;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(JdbcApplication.class, args);
//		 Console.main(args);
	}

}
