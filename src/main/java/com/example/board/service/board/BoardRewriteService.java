package com.example.board.service.board;

import com.example.board.dto.board.BoardRewriteDto;
import com.example.board.repository.board.BoardRewriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardRewriteService {
    private final BoardRewriteRepository boardRewriteRepository;

    @Transactional
    public void rewriteBoard(BoardRewriteDto boardRewriteDto) {
        boardRewriteRepository.rewriteBoard(boardRewriteDto);
    }
}
