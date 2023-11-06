package com.example.board.controller.user;

import com.example.board.domain.user.User;
import com.example.board.dto.user.LoginDto;
import com.example.board.service.user.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginHome(@CookieValue(value = "loginUser", required = false) User loginUser, Model model) {
        if (loginUser != null)
            model.addAttribute("checked", true);
        return "/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null)
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
        String ip = getRequestIp();
        log.info("ip={}", ip);
        return "redirect:/boards";
    }

    private static String getRequestIp() {
        String ip = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-RealIP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
