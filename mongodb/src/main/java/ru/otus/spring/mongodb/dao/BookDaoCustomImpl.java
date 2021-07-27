
package ru.otus.spring.mongodb.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.mongodb.domain.Comment;

@RequiredArgsConstructor
public class BookDaoCustomImpl implements BookDaoCustom {

	private final MongoTemplate mongoTemplate;

	@Override
	public void removeCommentsForBookByBookId(String id) {
		mongoTemplate.findAllAndRemove(new Query(Criteria.where("book.$id").is(new ObjectId(id))), Comment.class);
	}

	//неполучается удалить комент из массива книги. Коммент в листе почемуто остается.
//	@Override
//	public void removeCommentArrayElementsById(String id) {
//		var query = Query.query(Criteria.where("$id").is(new ObjectId(id)));
//		var update = new Update().pull("comments", query);
//		mongoTemplate.updateMulti(query, update, Book.class);	
//	}

}
