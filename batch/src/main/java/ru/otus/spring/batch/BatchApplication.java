package ru.otus.spring.batch;

import java.sql.SQLException;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.github.cloudyrock.spring.v5.EnableMongock;

@EnableMongock
@EnableConfigurationProperties
@SpringBootApplication
public class BatchApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(BatchApplication.class, args);
		Console.main(args);
	}

}
