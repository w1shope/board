package com.example.board.service.user;

import com.example.board.domain.user.User;
import com.example.board.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserListService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findUser(id);
    }
}
