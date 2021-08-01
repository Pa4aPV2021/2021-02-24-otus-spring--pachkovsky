package ru.otus.spring.mongodb;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.github.cloudyrock.spring.v5.EnableMongock;

@EnableMongock
@EnableConfigurationProperties
@SpringBootApplication
public class MongodbApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(MongodbApplication.class, args);

	}

}
