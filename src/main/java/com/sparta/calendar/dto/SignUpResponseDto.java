package com.sparta.calendar.dto;

import lombok.Getter;

//회원가입 기능
@Getter
public class SignUpResponseDto {

    private final Long id; // 아이디

    private final String username; //이름

    private final Integer age; //나이

    public SignUpResponseDto(Long id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }
}
