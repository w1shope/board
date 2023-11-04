package com.example.board.repository;

import com.example.board.domain.Board;
import com.example.board.dto.board.BoardEnrolDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
@Slf4j
public class BoardListRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardListRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long enrol(BoardEnrolDto boardEnrolDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt =
                    connection.prepareStatement("INSERT INTO Board(title,name, created_date, view_cnt) VALUES(?, ?, ?, ?)");
            stmt.setString(1, boardEnrolDto.getTitle());
            stmt.setString(2, boardEnrolDto.getName());
            stmt.setTimestamp(3, boardEnrolDto.getCreatedDate());
            stmt.setLong(4, boardEnrolDto.getViewCnt());
            return stmt;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<Board> boardList() {
        return jdbcTemplate.query("SELECT * FROM Board", (rs, rowNum) -> {
            return Board
                    .builder()
                    .id(rs.getLong("id"))
                    .title(rs.getString("title"))
                    .name(rs.getString("name"))
                    .createdDate(rs.getTimestamp("created_date"))
                    .formattedCreatedDate(Board.dateFormat().format(rs.getTimestamp("created_date")))
                    .viewCnt(rs.getLong("view_cnt"))
                    .build();
        });
    }

}