package com.example.board.service.board;

import com.example.board.dto.board.BoardDeleteDto;
import com.example.board.repository.board.BoardDeleteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardDeleteService {

    private final BoardDeleteRepository boardDeleteRepository;

    @Transactional
    public void deleteBoard(Long id) {
        boardDeleteRepository.deleteBoard(id);
    }
}
