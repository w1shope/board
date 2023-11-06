package com.example.board.repository.board;

import com.example.board.dto.board.BoardRewriteDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class BoardRewriteRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRewriteRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void rewriteBoard(BoardRewriteDto boardEditDto) {
        jdbcTemplate.update("UPDATE Board SET title = ?, content = ? WHERE id = ?", boardEditDto.getTitle(), boardEditDto.getContent(), boardEditDto.getId());
    }
}
