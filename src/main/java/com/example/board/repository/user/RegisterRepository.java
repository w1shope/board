package com.example.board.repository.user;

import com.example.board.dto.user.RegisterUserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class RegisterRepository {

    private final JdbcTemplate jdbcTemplate;

    public RegisterRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long registerUser(RegisterUserDto registerUserDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO User(name, login_id, password) VALUES(?,?,?)"
            , Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, registerUserDto.getName());
            stmt.setString(2, registerUserDto.getLoginId());
            stmt.setString(3, registerUserDto.getPassword());
            return stmt;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
