package com.sparta.calendar.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CreateBoardRequestDto {

    private final String title;
    private final String content;
    private final String username;

    @JsonCreator
    public CreateBoardRequestDto(  @JsonProperty("title") String title,
                                   @JsonProperty("content") String content,   @JsonProperty("username") String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }
}
