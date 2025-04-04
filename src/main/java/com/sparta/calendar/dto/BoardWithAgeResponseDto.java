package com.sparta.calendar.dto;

import lombok.Getter;

@Getter
public class BoardWithAgeResponseDto {

    private final String title;
    private final String content;
    private final Integer age;

    public BoardWithAgeResponseDto(String title, String content, Integer age) {
        this.title = title;
        this.content = content;
        this.age = age;
    }
}
