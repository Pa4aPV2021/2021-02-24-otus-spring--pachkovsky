package ru.otus.spring.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ru.otus.spring.jdbc.domain.Author;
import ru.otus.spring.jdbc.domain.Book;
import ru.otus.spring.jdbc.domain.Genre;

@Repository
public class BookDaoJDBC implements BookDao {

	private final NamedParameterJdbcOperations jdbc;

	public BookDaoJDBC(NamedParameterJdbcOperations jdbcOperations) {
		this.jdbc = jdbcOperations;
	}

	@Override
	public int count() {
		return jdbc.getJdbcOperations().queryForObject("select count(*) from book", Integer.class);
	}

	@Override
	public Book create(Book createdBook) {

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update("insert into book (name, id_author, id_genre) values (:name, :id_author, :id_genre)",
				new MapSqlParameterSource(Map.of("name", createdBook.getName(), "id_author",
						createdBook.getAuthor().getId(), "id_genre", createdBook.getGenre().getId())),
				keyHolder);

		return this.findOne(keyHolder.getKey().longValue());
	}

	@Override
	public void update(Book updatedBook) {
		jdbc.update("UPDATE book SET name=:name, id_author=:id_author, id_genre=:id_genre WHERE id=:id",
				Map.of("name", updatedBook.getName(), "id_author", updatedBook.getAuthor().getId(), "id_genre",
						updatedBook.getGenre().getId(), "id", updatedBook.getId()));
	}

	@Override
	public List<Book> findAll() {

		return jdbc.query(
				"SELECT b.id, b.name as book_name, id_author, a.name as author_name, id_genre, g.name as genre_name"
						+ "	FROM public.book as b" + "	join genre as g on (b.id_genre=g.id)"
						+ "	join author as a on (b.id_author=a.id)",
				this::mapRow);
	}

	@Override
	public void delete(Long id) {
		jdbc.update("delete from book where id = :id", Map.of("id", id));
	}

	@Override
	public Book findOne(Long id) {
		return jdbc.queryForObject(
				"SELECT b.id, b.name as book_name, id_author, a.name as author_name, id_genre, g.name as genre_name"
						+ "	FROM public.book as b" + "	join genre as g on (b.id_genre=g.id)"
						+ "	join author as a on (b.id_author=a.id) where b.id = :id",
				Map.of("id", id), this::mapRow);
	}

	private Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setId(rs.getLong("id"));
		book.setName(rs.getString("book_name"));
		book.setAuthor(new Author(rs.getLong("id_author"), rs.getString("author_name")));
		book.setGenre(new Genre(rs.getLong("id_genre"), rs.getString("genre_name")));
		return book;
	}

}
