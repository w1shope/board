package com.example.board.domain;

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
    private Long viewCnt;

    @Builder
    public Board(Long id, String title, String name, Timestamp createdDate, String formattedCreatedDate, Long viewCnt) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.createdDate = createdDate;
        this.formattedCreatedDate = formattedCreatedDate;
        this.viewCnt = viewCnt;
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");

    public static SimpleDateFormat dateFormat() {
        return dateFormat;
    }
}



