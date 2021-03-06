package com.noop.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 진행할 프로젝트의 메인 클래스
//@EnableJpaAuditing      // JPA Auditing을 활성화
@SpringBootApplication      // 스프링부트의 자동설정, 스프링 Bean읽기, 생성을 모두 자동화한다.
public class Application {
    public static  void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
