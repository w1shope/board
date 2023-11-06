package com.example.board.service.user;

import com.example.board.dto.user.RegisterUserDto;
import com.example.board.repository.user.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final RegisterRepository registerRepository;

    @Transactional
    public Long registerUser(RegisterUserDto registerUserDto) {
        return registerRepository.registerUser(registerUserDto);
    }
}
