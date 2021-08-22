package ru.otus.spring.batch.domain.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "persons")
public class PersonDocument {

	@Id
	private String id;
	@Field(name = "last-name")
	private String lastName;
	@Field(name = "first-name")
	private String firstName;
	
	@DBRef
	private List<CommentDocument> comments;

	public PersonDocument() {
	}

	public PersonDocument(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
	}

}