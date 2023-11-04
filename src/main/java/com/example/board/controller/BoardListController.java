package com.example.board.controller;

import com.example.board.domain.Board;
import com.example.board.service.BoardListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardListController {

    private final BoardListService boardListService;

    /**
     * Board 조회
     */
    @GetMapping("/boards")
    public String boardList(Model model) {
        List<Board> boards = boardListService.findAll();
        model.addAttribute("boards", boards);
        return "/list";
    }

    private String timeStampToString(Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 MM분");
        String formattedDate = dateFormat.format(timestamp);
        return formattedDate;
    }
}
