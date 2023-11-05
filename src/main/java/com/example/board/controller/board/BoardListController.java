package com.example.board.controller.board;

import com.example.board.domain.board.Board;
import com.example.board.service.board.BoardListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/boards/{id}")
    public String board(@PathVariable Long id, Model model) {
        Board board = boardListService.findById(id);
        model.addAttribute("board", board);
        return "/read";
    }
}
