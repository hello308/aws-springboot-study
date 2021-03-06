package com.noop.study.springboot.service;

import com.noop.study.springboot.domain.posts.Posts;
import com.noop.study.springboot.domain.posts.PostsRepository;
import com.noop.study.springboot.web.dto.PostsListResponseDto;
import com.noop.study.springboot.web.dto.PostsResponseDto;
import com.noop.study.springboot.web.dto.PostsSaveRequestDto;
import com.noop.study.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)     // readOnly = true -> CRUD 중 조회기능만 남겨두므로, 조회속도가 개선된다.
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)     // .map(posts -> new PostsListResponseDto(posts)) 와 같다.
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);      // JpaRepository 에서 이미 delete 메소드를 지원하고 있다.
    }
}
