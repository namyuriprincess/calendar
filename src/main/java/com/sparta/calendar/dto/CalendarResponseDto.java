package com.sparta.calendar.dto;

import com.sparta.calendar.entity.Calendar;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class CalendarResponseDto {

    private Long id;                 // 고유 ID
    private String title;           // 할 일 제목
    private String author;          // 작성자명
//    private String password;        // 비밀번호
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime modifiedAt; // 수정일

    public CalendarResponseDto(Calendar calendar) {
        this.id = calendar.getId();
        this.title = calendar.getTitle();
        this.author = calendar.getAuthor();
        this.createdAt = calendar.getCreatedAt();
        this.modifiedAt = calendar.getModifiedAt();

    }
}
