
package ru.otus.spring.mongodb.dao;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.val;
import ru.otus.spring.mongodb.domain.Book;

@RequiredArgsConstructor
public class BookDaoCustomImpl implements BookDaoCustom {

	@Data
	private class ArraySizeProjection {
		private int size;
	}

	private final MongoTemplate mongoTemplate;

//	@Override
//	public void removeCommentsForBookByBookId(String id) {
//		mongoTemplate.findAllAndRemove(new Query(Criteria.where("book.$id").is(new ObjectId(id))), Comment.class);
//	}

	// неполучается удалить комент из массива книги. Коммент в листе почемуто
	// остается.
	@Override
	public void removeCommentArrayElementsById(String id) {
		var query = Query.query(Criteria.where("$id").is(new ObjectId(id)));
		var update = new Update().pull("comments", query);
		mongoTemplate.updateMulti(query, update, Book.class);
	}

	@Override
	public long getCommentsArrayLengthByBookId(String bookId) {
		val aggregation = Aggregation.newAggregation(match(where("id").is(bookId)),
				project().andExclude("_id").and("comments").size().as("size"));

		val arraySizeProjection = mongoTemplate.aggregate(aggregation, Book.class, ArraySizeProjection.class)
				.getUniqueMappedResult();
		return arraySizeProjection == null ? 0 : arraySizeProjection.getSize();
	}

}
