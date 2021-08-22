package ru.otus.spring.batch.domain.document;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "books")
public class BookDocument {
	@Id
	private String id;
	@Field(name = "name")
	private String name;
	@DBRef
	private AuthorDocument author;
	@DBRef
	private GenreDocument genre;

	@DBRef
	private List<CommentDocument> comments;

	public BookDocument() {
	}

	public BookDocument(String id) {
		this.id = id;
	}

	public BookDocument(String name, AuthorDocument author, GenreDocument genre, List<CommentDocument> comments) {
		this.name = name;
		this.author = author;
		this.genre = genre;
		comments.stream().forEach(this::addComment);
	}

	public BookDocument(String name, AuthorDocument author, GenreDocument genre) {
		this.name = name;
		this.author = author;
		this.genre = genre;
	}

	public void addComment(CommentDocument comment) {

		if (comments == null) {
			comments = new ArrayList<CommentDocument>();
		}

		comments.add(comment);
		comment.setBook(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookDocument other = (BookDocument) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", genre=" + genre + "]";
	}

}
