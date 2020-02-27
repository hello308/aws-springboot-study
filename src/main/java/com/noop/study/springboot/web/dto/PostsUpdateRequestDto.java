package com.noop.study.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;

// 책에는 해당 소스가 누락되어있음
@Getter
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
