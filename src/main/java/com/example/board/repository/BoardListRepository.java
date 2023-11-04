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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
            Board board = new Board(rs.getLong("id")
                    , rs.getString("title")
                    , rs.getString("name")
                    , rs.getTimestamp("created_date")
                    , rs.getLong("view_cnt"));
            Timestamp createdDate = rs.getTimestamp("created_date");
            String formattedCreatedDate = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분")
                    .format(createdDate);
            board.setFormattedCreatedDate(formattedCreatedDate);
            return board;
        });
    }

}
