/*
 * Copyright 2016 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right name only.
 */
package ru.otus.spring.ajax.rest.dto;

import lombok.Data;
import ru.otus.spring.ajax.domain.Author;
import ru.otus.spring.ajax.domain.Book;
import ru.otus.spring.ajax.domain.Genre;

@Data
public class BookDto {

	private Long id;
	private String name;
	private Author author;
	private Genre genre;

	public BookDto(Long id, String name, Author author, Genre genre) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.genre = genre;
	}

	public static BookDto toDto(Book book) {
		return new BookDto(book.getId(), book.getName(), book.getAuthor(), book.getGenre());
	}

}
