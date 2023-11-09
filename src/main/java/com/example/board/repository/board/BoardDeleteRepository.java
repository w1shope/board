package com.example.board.repository.board;

import com.example.board.domain.board.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class BoardDeleteRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardDeleteRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void deleteBoard(Long id) {
        jdbcTemplate.update("DELETE FROM Board WHERE id = ?", id);
    }
}
