package com.example.board.repository.user;

import com.example.board.domain.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User findUser(Long userId) {
        return jdbcTemplate.queryForObject("SELECT * FROM User WHERE id = ?",
                (rs, rowNum) -> {
                    return User.builder()
                            .id(rs.getLong("id"))
                            .name(rs.getString("name"))
                            .loginId(rs.getString("login_id"))
                            .password(rs.getString("password"))
                            .boardId(rs.getLong("board_id"))
                            .build();
                }, userId);
    }
}
