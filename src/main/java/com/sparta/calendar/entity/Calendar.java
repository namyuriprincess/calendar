package com.sparta.calendar.entity;

import com.sparta.calendar.dto.CalendarRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Calendar {


    private Long id;                 // 고유 ID
    private String title;           // 할 일 제목
    private String author;          // 작성자명
    private String password;        // 비밀번호
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime modifiedAt; //수정일

    public void update(CalendarRequestDto dto){
        this.title = dto.getTitle();
        this.author = dto.getAuthor();

        // 날짜가 null이 아닐 때만 업데이트
        // 작성일과 수정일이 null이면 현재 시간으로 대체
        if (dto.getCreatedAt() != null) {
            this.createdAt = dto.getCreatedAt();
        } else {
            this.createdAt = LocalDateTime.now();
        }

        if (dto.getModifiedAt() != null) {
            this.modifiedAt = dto.getModifiedAt();
        } else {
            this.modifiedAt = LocalDateTime.now();
        }
    }
}