package com.example.board.controller.board;

import com.example.board.domain.board.Board;
import com.example.board.dto.board.BoardEnrolDto;
import com.example.board.service.board.BoardEnrolService;
import com.example.board.service.board.BoardListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardEnrolController {

    private final BoardEnrolService enrolService;
    private final BoardListService boardListService;

    @GetMapping("/enrol")
    public String openBoardEnrol(@ModelAttribute Board board) {
        return "/write";
    }

    @PostMapping("/enrol")
    public String insertBoardEnrol(BoardEnrolDto boardEnrolDto, Model model) {
        Long enrolBoardId = enrolService.enrolBoard(boardEnrolDto);
        Board findBoard = boardListService.findById(enrolBoardId);
        model.addAttribute("board", findBoard);
        return "/read";
    }

}
