package com.example.board.controller.user;

import com.example.board.dto.user.RegisterUserDto;
import com.example.board.service.user.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @GetMapping("/register")
    public String registerHome() {
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(RegisterUserDto registerUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/register";
        }
        registerService.registerUser(registerUserDto);
        return "redirect:/boards";
    }
}
