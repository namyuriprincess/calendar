package com.sparta.calendar.service;

import com.sparta.calendar.dto.BoardResponseDto;
import com.sparta.calendar.dto.BoardWithAgeResponseDto;
import com.sparta.calendar.entity.Board;
import com.sparta.calendar.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boarRepository;
    private final BoardRepository boardRepository;

    public BoardResponseDto save(String title, String content, String name) {

      User findUser = userRepository.findByUserByUsernameOrElseThrow(name);

        Board board = new Board(title, content);
        board.setUser(findUser);

       Board savedBoard = boardRepository.save(board);
        return new BoardResponseDto(savedBoard.getId(), savedBoard.getTitle(), savedBoard.getContent());
    }

    public List<BoardResponseDto> findAll() {
        return boardRepository.findAll()
             .stream()
             .map(BoardResponseDto::toDto)
             .toList();



    }

    public BoardWithAgeResponseDto findById(Long id) {

     Board findBoard = boardRepository.findByIdOrElseThrow(id);
       User user = findBoard.getUser();
        return new BoardWithAgeResponseDto(findBoard.getTitle(), findBoard.getContent(), user.getAge());
    }

    public void delete(Long id) {

        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        boardRepository.delete(findBoard);
    }
}
