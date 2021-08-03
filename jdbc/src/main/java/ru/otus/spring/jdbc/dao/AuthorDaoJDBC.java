package ru.otus.spring.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import ru.otus.spring.jdbc.domain.Author;

@Repository
public class AuthorDaoJDBC implements AuthorDao {
	private final NamedParameterJdbcOperations jdbc;

	public AuthorDaoJDBC(NamedParameterJdbcOperations jdbcOperations) {
		this.jdbc = jdbcOperations;
	}

	@Override
	public List<Author> findAll() {
		return jdbc.query("select id, name from author", this::mapRow);
	}

	@Override
	public Author findOne(Long id) {
		return jdbc.queryForObject("select id, name from author where id = :id", Map.of("id", id), this::mapRow);
	}

	private Author mapRow(ResultSet rs, int rowNum) throws SQLException {
		Author author = new Author();
		author.setId(rs.getLong("id"));
		author.setName(rs.getString("name"));
		return author;
	}
}
