package com.example.board.controller.user;

import com.example.board.domain.user.User;
import com.example.board.dto.user.LoginDto;
import com.example.board.service.user.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginHome() {
        return "/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if(session != null)
            session.invalidate();
        return "redirect:/boards";
    }

    @PostMapping("/login")
    public String loginUser(LoginDto loginDto, HttpSession session) {
        User loginUser = loginService.findByLoginIdAndPassword(loginDto);
        log.info("loginUser={}", loginUser);
        if (loginUser == null) {
            return "redirect:/login";
        }

        session.setAttribute("loginUser", loginUser);
        return "redirect:/boards";
    }

}
