package com.example.board.repository.board;

import com.example.board.domain.user.User;
import com.example.board.dto.board.BoardEnrolDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
@Slf4j
public class BoardEnrolRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardEnrolRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public Long enrolBoard(BoardEnrolDto boardEnrolDto, User loginUser) {
        log.info("loginUser={}", loginUser);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Board(title, name, content, created_date, view_cnt, user_id) VALUES(?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, boardEnrolDto.getTitle());
            stmt.setString(2, loginUser.getName());
            stmt.setString(3, boardEnrolDto.getContent());
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(5, 0L);
            stmt.setLong(6, loginUser.getId());
            return stmt;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
