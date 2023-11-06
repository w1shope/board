package com.example.board.repository.board;

import com.example.board.domain.board.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Slf4j
public class BoardListRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardListRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Board findBoard(Long id) {
        Board board = jdbcTemplate.queryForObject("SELECT * FROM Board WHERE id = ?",
                (rs, rowNum) -> {
                    return Board.builder()
                            .id(rs.getLong("id"))
                            .title(rs.getString("title"))
                            .name(rs.getString("name"))
                            .content(rs.getString("content"))
                            .createdDate(rs.getTimestamp("created_date"))
                            .viewCnt(rs.getLong("view_cnt"))
                            .build();
                }, id);

        // 조회수 증가
        jdbcTemplate.update("UPDATE Board SET view_cnt = view_cnt + 1 WHERE id = ?", board.getId());
        return board;
    }

    public List<Board> boardList() {
        return jdbcTemplate.query("SELECT * FROM Board", (rs, rowNum) -> {
            return Board
                    .builder()
                    .id(rs.getLong("id"))
                    .title(rs.getString("title"))
                    .name(rs.getString("name"))
                    .content(rs.getString("content"))
                    .createdDate(rs.getTimestamp("created_date"))
                    .formattedCreatedDate(Board.dateFormat().format(rs.getTimestamp("created_date")))
                    .viewCnt(rs.getLong("view_cnt"))
                    .build();
        });
    }

}