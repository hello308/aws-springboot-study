package com.noop.study.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter     // 롬복의 어노테이션
@NoArgsConstructor      // 롬복의 어노테이션
@Entity     // 테이블과 링크될 클래스임을 나타낸다.
public class Posts {
    @Id     // 해당 테이블의 PK 필드를 나타낸다
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // PK의 생성규칙을 나타낸다.
    private Long id;

    @Column(length = 500, nullable = false)     // 컬럼의 변경값이 있을때 사용한다. 생략 가능
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private  String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // 책에는 해당 소스가 누락되어있음
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
