package com.noop.study.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    // @Query 는 SpringDataJpa에서 제공하지 않는 메소드만 사용한다.
    // 하단의 쿼리는 Jpa만으로 사용가능하나, 예시를 보여준거임
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
