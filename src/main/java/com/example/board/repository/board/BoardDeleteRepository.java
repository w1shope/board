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
        Board board = jdbcTemplate.queryForObject("SELECT * FROM User WHERE board_id = ?", (rs, rowNum) -> {
            return Board.builder()
                    .id(id)
                    .build();
        }, id);
        if(board != null) {
            jdbcTemplate.update("UPDATE User SET board_id = null where board_id = ?", id);
        }
        jdbcTemplate.update("DELETE FROM Board WHERE id = ?", id);
    }
}
