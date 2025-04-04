package com.sparta.calendar.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//회원가입 기능
@Getter
@RequiredArgsConstructor
public class SignUpRequestDto {

    private String username;

    private final String password;

    private final Integer age;

    @JsonCreator
    public SignUpRequestDto(@JsonProperty("username") String username,
                            @JsonProperty("password") String password,
                            @JsonProperty("age") Integer age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }


}

