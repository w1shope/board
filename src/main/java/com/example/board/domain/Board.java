package com.example.board.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class Board {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String name;
    @NotBlank
    private Timestamp createdDate;
    @NotBlank
    private String formattedCreatedDate;
    @NotBlank
    private Long viewCnt;

    public Board(Long id, String title, String name, Timestamp createdDate, Long viewCnt) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.createdDate = createdDate;
        this.viewCnt = viewCnt;
    }
}
