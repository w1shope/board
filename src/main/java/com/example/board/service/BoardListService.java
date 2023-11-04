package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.dto.board.BoardEnrolDto;
import com.example.board.repository.BoardListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardListService {
    private final BoardListRepository boardListRepository;
    @Transactional
    public Long enrolBoard(BoardEnrolDto boardEnrolDto) {
        return boardListRepository.enrol(boardEnrolDto);
    }

    @Transactional
    public List<Board> findAll() {
        return boardListRepository.boardList();
    }
}

