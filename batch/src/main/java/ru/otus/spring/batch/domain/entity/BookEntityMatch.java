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
@Table(name = "book_entity_match")
public class BookEntityMatch {
	
	
	@Id
	@Column(name = "book_entity_id")
	private Long bookEntityId;
	@Column(name = "book_mongo_id")
	private String bookMongoId;

	@OneToOne
    @MapsId
    @JoinColumn(name = "book_entity_id")
    private BookEntity bookEntity;
	
	
	

}


