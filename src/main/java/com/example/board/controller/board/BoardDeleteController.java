package com.example.board.controller.board;

import com.example.board.dto.board.BoardDeleteDto;
import com.example.board.service.board.BoardDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardDeleteController {

    private final BoardDeleteService boardDeleteService;

    @PostMapping("/boards/delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardDeleteService.deleteBoard(id);
        return "redirect:/boards";
    }
}
