package ru.otus.spring.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
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
	private final GenreDao genreDao;
	private final AuthorDao authorDao;

	public BookDaoJDBC(NamedParameterJdbcOperations jdbcOperations, GenreDao genreDao, AuthorDao authorDao) {
		this.jdbc = jdbcOperations;
		this.genreDao = genreDao;
		this.authorDao = authorDao;
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

		return jdbc.query("select id, name, id_author, id_genre from book", this::extractData);
	}

	@Override
	public void delete(Long id) {
		jdbc.update("delete from book where id = :id", Map.of("id", id));
	}

	@Override
	public Book findOne(Long id) {
		return jdbc.queryForObject("select id, name, id_author, id_genre from book where id = :id", Map.of("id", id),
				this::mapRow);
	}

	private Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setId(rs.getLong("id"));
		book.setName(rs.getString("name"));
		book.setAuthor(this.authorDao.findOne(rs.getLong("id_author")));
		book.setGenre(this.genreDao.findOne(rs.getLong("id_genre")));
		return book;
	}

	private List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Long, Author> authorsMap = this.authorDao.findAll().stream()
				.collect(Collectors.toMap(Author::getId, a -> a));
		Map<Long, Genre> genresMap = this.genreDao.findAll().stream().collect(Collectors.toMap(Genre::getId, g -> g));
		List<Book> books = new ArrayList<Book>();
		while (rs.next()) {
			books.add(new Book(rs.getLong("id"), rs.getString("name"), authorsMap.get(rs.getLong("id_author")),
					genresMap.get(rs.getLong("id_genre"))));
		}
		return books;
	}

}
