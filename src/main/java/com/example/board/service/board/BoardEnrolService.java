package com.example.board.service.board;

import com.example.board.domain.user.User;
import com.example.board.dto.board.BoardEnrolDto;
import com.example.board.repository.board.BoardEnrolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardEnrolService {
    private final BoardEnrolRepository boardEnrolRepository;

    public Long enrolBoard(BoardEnrolDto boardEnrolDto, User loginUser) {
        return boardEnrolRepository.enrolBoard(boardEnrolDto, loginUser);
    }
}
