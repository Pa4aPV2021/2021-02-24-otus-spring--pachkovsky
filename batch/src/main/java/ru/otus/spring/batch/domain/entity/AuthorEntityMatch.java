package ru.otus.spring.batch.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "author_entity_match")
public class AuthorEntityMatch {

	@Id
	@Column(name = "author_entity_id")
	private Long authorEntityId;
	@Column(name = "author_mongo_id")
	private String authorMongoId;

	@OneToOne
    @MapsId
    @JoinColumn(name = "author_entity_id")
    private AuthorEntity authorEntity;
	
	
}