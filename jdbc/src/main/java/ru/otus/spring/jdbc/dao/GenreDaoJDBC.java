package ru.otus.spring.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import ru.otus.spring.jdbc.domain.Genre;

@Repository
public class GenreDaoJDBC implements GenreDao {
	private final NamedParameterJdbcOperations jdbc;

	public GenreDaoJDBC(NamedParameterJdbcOperations jdbcOperations) {
		this.jdbc = jdbcOperations;
	}

	@Override
	public List<Genre> findAll() {
		return jdbc.query("select id, name from genre", this::mapRow);
	}

	@Override
	public Genre findOne(Long id) {
		return jdbc.queryForObject("select id, name from genre where id = :id", Map.of("id", id), this::mapRow);
	}

	private Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
		Genre genre = new Genre();
		genre.setId(rs.getLong("id"));
		genre.setName(rs.getString("name"));
		return genre;
	}

}
