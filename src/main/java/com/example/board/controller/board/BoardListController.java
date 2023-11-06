package com.example.board.controller.board;

import com.example.board.domain.board.Board;
import com.example.board.domain.user.User;
import com.example.board.service.board.BoardListService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardListController {

    private final BoardListService boardListService;

    /**
     * Board 조회
     */
    @GetMapping("/boards")
    public String boardList(Model model, HttpSession session) {
        List<Board> boards = boardListService.findAll();
        model.addAttribute("boards", boards);

        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);
        return "/list";
    }

    @GetMapping("/boards/{id}")
    public String board(@PathVariable Long id, Model model) {
        Board board = boardListService.findById(id);
        model.addAttribute("board", board);
        return "/read";
    }


}
