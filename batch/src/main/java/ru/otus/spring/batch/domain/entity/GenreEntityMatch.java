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
@Table(name = "genre_entity_match")
public class GenreEntityMatch {

	@Id
	@Column(name = "genre_entity_id")
	private Long genreEntityId;
	@Column(name = "genre_mongo_id")
	private String genreMongoId;

	@OneToOne
    @MapsId
    @JoinColumn(name = "genre_entity_id")
    private GenreEntity genreEntity;
	
	
}
