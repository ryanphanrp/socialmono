package org.ryan.domain;

import lombok.RequiredArgsConstructor;
import org.ryan.application.dto.PostCreateDto;
import org.ryan.application.dto.PostDto;
import org.ryan.exception.customize.CustomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostDao postDao;

    public PostDto getPost(Long postId) {
        Post post = postDao.findPostByPostId(postId)
                .orElseThrow(CustomNotFoundException::new);
        return PostDto.of(post);
    }

    public Long createPost(PostCreateDto postCreateDto) {
        Post post = postCreateDto.toEntity();
        postDao.save(post);
        return post.getPostId();
    }

    public List<PostDto> getPosts() {
        return postDao.findAll().stream()
                .map(PostDto::of)
                .toList();
    }
}
