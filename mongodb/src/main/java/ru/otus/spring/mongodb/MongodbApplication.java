package ru.otus.spring.mongodb;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongodbApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(MongodbApplication.class, args);
	}

}
