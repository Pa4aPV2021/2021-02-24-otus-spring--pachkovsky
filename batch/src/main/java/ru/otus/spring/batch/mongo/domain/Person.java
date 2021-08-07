package ru.otus.spring.batch.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "persons")
public class Person {

	@Id
	private String id;
	@Field(name = "last-name")
	private String lastName;
	@Field(name = "first-name")
	private String firstName;

	public Person() {
	}

	public Person(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
	}

}