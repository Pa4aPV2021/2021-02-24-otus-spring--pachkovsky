package ru.otus.spring.batch.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class BookEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@ManyToOne(targetEntity = AuthorEntity.class, cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_author")
	private AuthorEntity author;
	@ManyToOne(targetEntity = GenreEntity.class, cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_genre")
	private GenreEntity genre;
	
	@OneToOne(mappedBy = "bookEntity", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private BookEntityMatch bookEntityMatch;
 
	public BookEntity(Long id) {
		this.id = id;
	}

	public BookEntity(String name, AuthorEntity author, GenreEntity genre, List<CommentEntity> comments) {
		this.name = name;
		this.author = author;
		this.genre = genre;
	}

	public BookEntity(String name, AuthorEntity author, GenreEntity genre) {
		this.name = name;
		this.author = author;
		this.genre = genre;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookEntity other = (BookEntity) obj;
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
