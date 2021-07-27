package ru.otus.spring.mongodb.dao;

public interface BookDaoCustom {
	void removeCommentsForBookByBookId(String id);
//	void removeCommentArrayElementsById(String id);
}