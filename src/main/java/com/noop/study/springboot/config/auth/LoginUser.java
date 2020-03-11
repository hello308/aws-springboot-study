package com.noop.study.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  // 현재의 어노테이션이 생설될 수 있는 위치를 지정
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {   // @interface 어노테이션 클래스임을 지정한다.
}
