package com.sparta.calendar.controller;


import com.sparta.calendar.dto.BoardResponseDto;
import com.sparta.calendar.dto.BoardWithAgeResponseDto;
import com.sparta.calendar.dto.CreateBoardRequestDto;
import com.sparta.calendar.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponseDto> save(@RequestBody CreateBoardRequestDto requestDto) {

       BoardResponseDto save =  boardService.save(requestDto.getTitle(),requestDto.getContent(),requestDto.getUsername());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll() {

      List<BoardResponseDto> boardResponseDtoList = boardService.findAll();

        return new ResponseEntity<>(boardResponseDtoList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardWithAgeResponseDto> findById(@PathVariable("id") Long id) {

      BoardWithAgeResponseDto boardWithAgeResponseDto =  boardService.findById(id);

        return new ResponseEntity<>(boardWithAgeResponseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        boardService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
