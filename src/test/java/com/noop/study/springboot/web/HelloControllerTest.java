package com.noop.study.springboot.web;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)        // JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
@WebMvcTest     // 스프링 어노테이션중 Web에 집중 할 수 있는 어노테이션
public class HelloControllerTest {

    @Autowired      // 스프링이 관리하는 Bean을 주입받는다.
    private MockMvc mvc;        // 웹 API를 테스트할때 사용된다.

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(MockMvcRequestBuilders.get("/hello"))   // GET 요청을 가능하게 한다.
                .andExpect(MockMvcResultMatchers.status().isOk())   // OK인 200인지 아닌지 검증한다.
                .andExpect(MockMvcResultMatchers.content().string(hello));  // "hello"인 값이 맞는지 검증한다.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(MockMvcRequestBuilders.get("/hello/dto")
                .param("name",name)     // API 테스트할 떄 사용될 파라미터를 설정, String만 허용
                .param("amount",String.valueOf(amount)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount",Matchers.is(amount)));
        // jsonPath JSON응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명을 명시한다.
    }
}
