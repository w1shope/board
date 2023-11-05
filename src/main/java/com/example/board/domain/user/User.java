package com.example.board.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private Long id;
    private String name;
    private String loginId;
    private String password;
    private Long boardId;

    @Builder
    public User(Long id, String name, String loginId, String password, Long boardId) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.boardId = boardId;
    }
}
