package com.example.board.dto.board;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class BoardEnrolDto {
    private String title;
    private String name;
    private Timestamp createdDate;
    private Long viewCnt;
}
