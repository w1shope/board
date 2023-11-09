package com.example.board.controller.board;

import com.example.board.domain.board.Board;
import com.example.board.domain.user.User;
import com.example.board.dto.board.BoardEnrolDto;
import com.example.board.service.board.BoardEnrolService;
import com.example.board.service.board.BoardListService;
import com.example.board.service.user.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardEnrolController {

    private final BoardEnrolService enrolService;
    private final BoardListService boardListService;
    private final LoginService loginService;

    @GetMapping("/enrol")
    public String openBoardEnrol(@ModelAttribute Board board, HttpSession session, Model model)
    {
        User loginUser = (User)session.getAttribute("loginUser");
        log.info("loginUser={}", loginUser);
        if(loginUser != null)
            model.addAttribute("loginUser", loginUser);
        return "/write";
    }

    @PostMapping("/enrol")
    public String insertBoardEnrol(BoardEnrolDto boardEnrolDto, String loginId, Model model, RedirectAttributes redirectAttributes) {
        log.info("loginId={}", loginId);
        User loginedUser = loginService.findByLoginId(loginId);
        Long enrolBoardId = enrolService.enrolBoard(boardEnrolDto, loginedUser);
        Board findBoard = boardListService.findById(enrolBoardId);
        model.addAttribute("board", findBoard);
        redirectAttributes.addAttribute("id", enrolBoardId);
        return "redirect:/boards/{id}";
    }

}
