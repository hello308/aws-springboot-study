package com.noop.study.springboot.web.dto;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        Assertions.assertThat(dto.getName()).isEqualTo(name);       // assertThat 테스트 검증 라이브러리의 검증 메소드
        Assertions.assertThat(dto.getAmount()).isEqualTo(amount);   // isEqualTo 동등비교 메소드
    }
}
