package com.example.board.service.user;

import com.example.board.domain.user.User;
import com.example.board.dto.user.LoginDto;
import com.example.board.repository.user.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    @Transactional
    public User findByLoginIdAndPassword(LoginDto loginDto){
        return loginRepository.findUser(loginDto);
    }

    @Transactional
    public User findByLoginId(String loginId) {
        return loginRepository.findByLoginId(loginId);
    }
}
