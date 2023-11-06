package com.example.board.controller.board;

import com.example.board.domain.board.Board;
import com.example.board.dto.board.BoardRewriteDto;
import com.example.board.service.board.BoardListService;
import com.example.board.service.board.BoardRewriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardRewriteController {

    private final BoardRewriteService boardRewriteService;
    private final BoardListService boardListService;

    @GetMapping("/boards/rewrite/{id}")
    public String openBoard(@PathVariable Long id, Model model) {
        Board board = boardListService.findById(id);
        model.addAttribute("board", board);
        return "/rewrite";
    }

    @PostMapping("/boards/rewrite")
    public String rewriteBoard(BoardRewriteDto boardRewriteDto, RedirectAttributes redirectAttributes) {
        log.info("dto={}", boardRewriteDto);
        boardRewriteService.rewriteBoard(boardRewriteDto);
        redirectAttributes.addAttribute("id", boardRewriteDto.getId());
        return "redirect:/boards/{id}";
    }

}
