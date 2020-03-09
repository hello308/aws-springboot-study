package com.noop.study.springboot.config.auth;

import com.noop.study.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security설정들을 활성화 한다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf().disable().headers().frameOptions().disable() -> h2-console 화면을 사용하기 위해 해당 옵션들을 disable 한다.
        http.csrf().disable().headers().frameOptions().disable().and().authorizeRequests()  // authorizeRequests() -> URL별 권한관리 설정옵션의 시작점
                .antMatchers("/", "css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()   // permitAll() -> 해당 속성은 전체열람 권한
                .antMatchers("/api/**").hasRole(Role.USER.name())   // hasRole(Role.USER.name()) -> USER 권한이 있는 사람에게만 가능하게 한다.
                .anyRequest().authenticated().and().logout().logoutSuccessUrl("/").and()    // 설정값 이외의 URL들을 나타낸다. authenticated() 추가시 로그인사용자만
                                                                                            // logout().logoutSuccessUrl("/") 로그아웃시 / 로 이동
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService); // oauth2Login() -> OAuth2 로그인 기능에 대한 설정의 진입점
                                                                                        // userInfoEndpoint() -> 로그인 성공 후 사용자 정보를 가져올때의 설정 담당
                                                                                        // userService( -> 로그인 성공시 후속 조치할 서비스를 지정
    }
}
