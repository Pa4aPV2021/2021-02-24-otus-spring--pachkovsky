package ru.otus.spring.batch.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "author")
public class AuthorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	
	@OneToOne(mappedBy = "authorEntity", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private AuthorEntityMatch authorEntityMatch;

	public AuthorEntity(Long id) {
		this.id = id;
	}

	public AuthorEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public AuthorEntity(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorEntity other = (AuthorEntity) obj;
		if (authorEntityMatch == null) {
			if (other.authorEntityMatch != null)
				return false;
		} else if (!authorEntityMatch.equals(other.authorEntityMatch))
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
		result = prime * result + ((authorEntityMatch == null) ? 0 : authorEntityMatch.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "AuthorEntity [id=" + id + ", name=" + name + "]";
	}

	

	

}
