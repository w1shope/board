package com.example.board.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
