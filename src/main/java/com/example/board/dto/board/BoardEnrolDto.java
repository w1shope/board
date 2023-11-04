package com.example.board.dto.board;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class BoardEnrolDto {
    private String title;
    private String name;
    private Timestamp createdDate;
    private Long viewCnt;
}
