package com.example.board.domain.board;

import com.example.board.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


@Getter
public class Board {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String name;
    @NotBlank
    private Timestamp createdDate;
    private String formattedCreatedDate;
    @NotBlank
    private String content;
    @NotBlank
    private Long viewCnt;
    @NotBlank
    private Long userId;

    @Builder
    private Board(Long id, String title, String name, String content, Timestamp createdDate, String formattedCreatedDate, Long viewCnt, Long userId) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.content = content;
        this.createdDate = createdDate;
        this.formattedCreatedDate = formattedCreatedDate;
        this.viewCnt = viewCnt;
        this.userId = userId;
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");

    public static SimpleDateFormat dateFormat() {
        return dateFormat;
    }
}



