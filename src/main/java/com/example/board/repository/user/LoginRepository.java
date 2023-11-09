package com.example.board.repository.user;

import com.example.board.domain.user.User;
import com.example.board.dto.user.LoginDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class LoginRepository {

    private final JdbcTemplate jdbcTemplate;

    public LoginRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 회원 조회
     */
    public User findUser(LoginDto loginDto) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM User WHERE login_id = ? AND password = ?",
                    (rs, rowNum) -> {
                        return User.builder()
                                .id(rs.getLong("id"))
                                .name(rs.getString("name"))
                                .loginId(rs.getString("login_id"))
                                .password(rs.getString("password"))
                                .build();
                    }, loginDto.getLoginId(), loginDto.getPassword());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
