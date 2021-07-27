package ru.otus.spring.ajax;

import java.sql.SQLException;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AjaxApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(AjaxApplication.class, args);
		 Console.main(args);
	}

}
