package com.example.board.service.board;

import com.example.board.domain.board.Board;
import com.example.board.repository.board.BoardListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardListService {
    private final BoardListRepository boardListRepository;

    @Transactional
    public Board findById(Long id) {
        return boardListRepository.findBoard(id);
    }

    @Transactional
    public List<Board> findAll() {
        return boardListRepository.boardList();}
}

